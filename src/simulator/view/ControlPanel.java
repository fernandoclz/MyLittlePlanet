package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{

	private Controller _ctrl;
	private JToolBar _toolaBar;
	private JFileChooser _fc;
	private boolean _stopped = true; // utilizado en los botones de run/stop
	private JButton _quitButton;
	
	// TODO añade más atributos aquí …
	private JButton _ficherosB;
	private JButton _forceLawsB;
	private JButton _viewerB;
	private JButton _runB;
	private JButton _stopB;
	private JSpinner _stepsSpinner;
	private JTextField _timeTextField;
	private ForceLawsDialog _forceD;
	private ViewerWindow _viewer;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		// TODO registrar this como observador
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		_toolaBar = new JToolBar();
		add(_toolaBar, BorderLayout.PAGE_START);
		
		// TODO crear los diferentes botones/atributos y añadirlos a _toolaBar.
		// 		Todos ellos han de tener su correspondiente tooltip. Puedes utilizar
		// 		_toolaBar.addSeparator() para añadir la línea de separación vertical
		// 		entre las componentes que lo necesiten
		
		//Ficheros Button
		_ficherosB = new JButton();
		_ficherosB.setToolTipText("Files");
		_ficherosB.setIcon(new ImageIcon("resources/icons/open.png"));
		_ficherosB.addActionListener((e) -> {
			_fc = new JFileChooser("resources/examples/input");
			int f = _fc.showOpenDialog(Utils.getWindow(this));
			if(f == JFileChooser.APPROVE_OPTION) { // ha seleccionado un fichero
				_ctrl.reset();
				File file = _fc.getSelectedFile();
				String fileName = file.getAbsolutePath();
				//carga el fichero seleccionado en el simulador
				InputStream inFile = null;
				try {
					inFile = new FileInputStream(fileName);
					_ctrl.loadData(inFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					Utils.showErrorMsg(e1.getMessage());
				}
			}
		});
		_toolaBar.add(_ficherosB);
		_toolaBar.addSeparator();
		
		//ForceLaws Button
		_forceLawsB = new JButton();
		_forceLawsB.setToolTipText("Force Laws");
		_forceLawsB.setIcon(new ImageIcon("resources/icons/physics.png"));
		_forceLawsB.addActionListener((e) -> {
			if(_forceD == null) {
				_forceD = new ForceLawsDialog( (JFrame) SwingUtilities.getWindowAncestor(ControlPanel.this)
						, _ctrl);
			}
			_forceD.open();
		});
		_toolaBar.add(_forceLawsB);
		
		//Viewer Button
		_viewerB = new JButton();
		_viewerB.setToolTipText("Viewer");
		_viewerB.setIcon(new ImageIcon("resources/icons/viewer.png"));
		_viewerB.addActionListener((e) -> {
			JFrame frame = null;
			_viewer = new ViewerWindow(frame, _ctrl);
		});
		_toolaBar.add(_viewerB);
		
		_toolaBar.addSeparator();
		//Run Button
		_runB = new JButton();
		_runB.setToolTipText("Start");
		_runB.setIcon(new ImageIcon("resources/icons/run.png"));
		_runB.addActionListener((e) -> {
			//1
			this._stopped = false;
			settingEnable();
			//2
			Double t = Double.parseDouble(_timeTextField.getText());
			if(t > 0) {
			this._ctrl.setDeltaTime(Double.parseDouble(_timeTextField.getText()));
			}
			//3
			int count = Integer.parseInt(_stepsSpinner.getValue().toString());
			run_sim(count);
		});
		_toolaBar.add(_runB);
		
		//Stop Button
		_stopB = new JButton();
		_stopB.setToolTipText("Stop");
		_stopB.setIcon(new ImageIcon("resources/icons/stop.png"));
		_stopB.addActionListener((e) -> {
			this._stopped = true;
		});
		_toolaBar.add(_stopB);
		
		JLabel _stepsL = new JLabel("Steps: ");
		_toolaBar.add(_stepsL);
		_stepsSpinner = new JSpinner(new SpinnerNumberModel(1000,0,1000000,100));
		_stepsSpinner.setPreferredSize(new Dimension(80,30));
		_stepsSpinner.setMaximumSize(new Dimension(80,30));
		_toolaBar.add(_stepsSpinner);
		
		JLabel _timeL = new JLabel("Delta-Time: ");
		_toolaBar.add(_timeL);
		_timeTextField = new JTextField();
		_timeTextField.setPreferredSize(new Dimension(80,30));
		_timeTextField.setMaximumSize(new Dimension(80,30));
		_toolaBar.add(_timeTextField);
		
		// Quit Button
		_toolaBar.add(Box.createGlue()); // this aligns the button to the right
		_toolaBar.addSeparator();
		_quitButton = new JButton();
		_quitButton.setToolTipText("Quit");
		_quitButton.setIcon(new ImageIcon("resources/icons/exit.png"));
		_quitButton.addActionListener((e) -> Utils.quit(this));
		_toolaBar.add(_quitButton);
		
		// TODO crear el selector de ficheros
		_fc = new JFileChooser();
		
	}
	
	// TODO el resto de métodos van aquí…
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
		try {
		_ctrl.run(1);
		} catch (Exception e) {
		// TODO llamar a Utils.showErrorMsg con el mensaje de error que
		// corresponda
			Utils.showErrorMsg(e.toString());
		// TODO activar todos los botones
			_stopped = true;
			settingEnable();
		return;
		}
		SwingUtilities.invokeLater(() -> run_sim(n - 1));
		} else {
		// TODO activar todos los botones
			_stopped = true;
			settingEnable();
		}
	}
	
	
	private void settingEnable() {
		this._ficherosB.setEnabled(_stopped);
		this._forceLawsB.setEnabled(_stopped);
		this._viewerB.setEnabled(_stopped);
		this._runB.setEnabled(_stopped);
		this._stepsSpinner.setEnabled(_stopped);
		this._timeTextField.setEnabled(_stopped);
	}
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		this._timeTextField.setText("" + dt);
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		this._timeTextField.setText("" + dt);
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}

}
