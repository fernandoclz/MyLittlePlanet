package simulator.launcher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.MovingBodyBuilder;
import simulator.factories.MovingTowardsFixedPointBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoForceBuilder;
import simulator.factories.StationaryBodyBuilder;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.PhysicsSimulator;
import simulator.view.MainWindow;


public class Main {

	// default values for some parameters
	//
	private final static Integer _stepsDefaultValue = 150;
	private final static Double _dtimeDefaultValue = 2500.0;
	private final static String _forceLawsDefaultValue = "nlug";
	private final static String _modeDefaultValue = "gui";

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Integer _steps = null;
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static String _mode = null;
	private static JSONObject _forceLawsInfo = null;

	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<ForceLaws> _forceLawsFactory;

	private static void initFactories() {
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		ArrayList<Builder<ForceLaws>> forceLawsFactory = new ArrayList<>();
		
		bodyBuilders.add(new MovingBodyBuilder());
		bodyBuilders.add(new StationaryBodyBuilder());
		
		forceLawsFactory.add(new MovingTowardsFixedPointBuilder());
		forceLawsFactory.add(new NewtonUniversalGravitationBuilder());
		forceLawsFactory.add(new NoForceBuilder());
		
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		_forceLawsFactory = new BuilderBasedFactory<ForceLaws>(forceLawsFactory);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseDeltaTimeOption(line);
			parseForceLawsOption(line);
			parseOutFileOption(line);
			parseStepsOption(line);
			parseExecutionMode(line);
			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}


	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());

		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());
		//execution-mode
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Execution Mode. Possible values: 'batch' (Batch\n"
				+ "mode), 'gui' (Graphical User Interface mode).\n"
				+ "Default value: 'gui'.")
				.build());

		// force laws
		cmdLineOptions.addOption(Option.builder("fl").longOpt("force-laws").hasArg()
				.desc("Force laws to be used in the simulator. Possible values: "
						+ factoryPossibleValues(_forceLawsFactory) + ". Default value: '" + _forceLawsDefaultValue
						+ "'.")
				.build());
		
		// out file
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg()
				.desc(" Output file, where output is written. Default\n value: the standard output.\n")
				.build());
		// steps
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg()
				.desc("An integer representing the number of simulation steps. Default value: " + _stepsDefaultValue + ".")
				.build());
		return cmdLineOptions;
	}

	public static String factoryPossibleValues(Factory<?> factory) {
		String s = "";
		if (factory != null) {

			for (JSONObject fe : factory.getInfo()) {
				if (s.length() > 0) {
					s = s + ", ";
				}
				s = s + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}

			s = s + ". You can provide the 'data' json attaching :{...} to the tag, but without spaces.";
		} else {
			s = "No values found";
		}
		return s;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
	}

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static JSONObject parseWRTFactory(String v, Factory<?> factory) {

		// the value of v is either a tag for the type, or a tag:data where data is a
		// JSON structure corresponding to the data of that type. We split this
		// information
		// into variables 'type' and 'data'
		//
		int i = v.indexOf(":");
		String type = null;
		String data = null;
		if (i != -1) {
			type = v.substring(0, i);
			data = v.substring(i + 1);
		} else {
			type = v;
			data = "{}";
		}

		// look if the type is supported by the factory
		boolean found = false;
		if (factory != null) {
			for (JSONObject fe : factory.getInfo()) {
				if (type.equals(fe.getString("type"))) {
					found = true;
					break;
				}
			}
		}

		// build a corresponding JSON for that data, if found
		JSONObject jo = null;
		if (found) {
			jo = new JSONObject();
			jo.put("type", type);
			jo.put("data", new JSONObject(data));
		}
		return jo;

	}

	private static void parseForceLawsOption(CommandLine line) throws ParseException {
		String fl = line.getOptionValue("fl", _forceLawsDefaultValue);
		_forceLawsInfo = parseWRTFactory(fl, _forceLawsFactory);
		if (_forceLawsInfo == null) {
			throw new ParseException("Invalid force laws: " + fl);
		}
	}
	private static void parseOutFileOption(CommandLine line) throws ParseException{
		// TODO Auto-generated method stub
		_outFile = line.getOptionValue("o");
		if (_outFile == null && _mode == "batch") {
			throw new ParseException("In batch mode an output file of bodies is required");
		}
	}
	
	private static void parseStepsOption(CommandLine line) throws ParseException {
		// TODO Auto-generated method stub
		String steps = line.getOptionValue("s", _stepsDefaultValue.toString());
		try {
			_steps = Integer.parseInt(steps);
			assert(_steps > 0);
		}
		catch(Exception e){
			throw new ParseException("Invalid steps value: " + steps);
		}
	}
	
	private static void parseExecutionMode(CommandLine line) throws ParseException {
		_mode = line.getOptionValue("m", _modeDefaultValue);
		if(!(_mode.equals("batch") || _mode.equals("gui"))) {
			throw new ParseException("Invalid mode value");
		}
	}
	
	private static void startGUIMode() throws Exception{
		InputStream instream = null;
		try {
		instream = new FileInputStream(_inFile);
		}catch(Exception e){
			
		}
		ForceLaws fl = _forceLawsFactory.createInstance(_forceLawsInfo);
		PhysicsSimulator sim = new PhysicsSimulator(fl, _dtime);
		Controller ctrl = new Controller(sim, _forceLawsFactory, _bodyFactory);
		if(instream != null)
			ctrl.loadData(instream);
		SwingUtilities.invokeAndWait(() -> new MainWindow(ctrl));
	}
	private static void startBatchMode() throws Exception {
		InputStream instream = new FileInputStream(_inFile);
		OutputStream outstream = new FileOutputStream(_outFile);
		ForceLaws fl = _forceLawsFactory.createInstance(_forceLawsInfo);
		PhysicsSimulator sim = new PhysicsSimulator(fl, _dtime);
		Controller ctrl = new Controller(sim, _forceLawsFactory, _bodyFactory);
		
		ctrl.loadData(instream);
		ctrl.run(_steps, outstream);
	}
	
	

	private static void start(String[] args) throws Exception {
		parseArgs(args);
		if(_mode.equals("gui")) {
			startGUIMode();
		}
		else if(_mode.equals("batch")){
			startBatchMode();
		}
	}

	public static void main(String[] args) {
		try {
			initFactories();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
