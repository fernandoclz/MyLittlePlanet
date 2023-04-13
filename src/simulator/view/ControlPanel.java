package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;

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
			int f = _fc.showOpenDialog(Utils.getWindow(this));
			if(f == JFileChooser.APPROVE_OPTION) { // ha seleccionado un fichero
				_ctrl.reset();
				File file = _fc.getSelectedFile();
				//carga el fichero seleccionado en el simulador
				//_ctrl.loadData(file);
			}
		});
		_toolaBar.add(_ficherosB);
		_toolaBar.addSeparator();
		
		//ForceLaws Button
		_forceLawsB = new JButton();
		_forceLawsB.setToolTipText("Force Laws");
		_forceLawsB.setIcon(new ImageIcon("resources/icons/physics.png"));
		_forceLawsB.addActionListener((e) -> Utils.quit(this));
		_toolaBar.add(_forceLawsB);
		
		//Viewer Button
		_viewerB = new JButton();
		_viewerB.setToolTipText("Viewer");
		_viewerB.setIcon(new ImageIcon("resources/icons/viewer.png"));
		_viewerB.addActionListener((e) -> Utils.quit(this));
		_toolaBar.add(_viewerB);
		
		_toolaBar.addSeparator();
		//Run Button
		_runB = new JButton();
		_runB.setToolTipText("Start");
		_runB.setIcon(new ImageIcon("resources/icons/run.png"));
		_runB.addActionListener((e) -> {
			//1
			this._ficherosB.setEnabled(false);
			this._forceLawsB.setEnabled(false);
			this._viewerB.setEnabled(false);
			this._runB.setEnabled(false);
			this._stopped = false;
			//2
	//		this._ctrl.setDeltaTime();
			//3
		});
		_toolaBar.add(_runB);
		
		//Stop Button
		_stopB = new JButton();
		_stopB.setToolTipText("Stop");
		_stopB.setIcon(new ImageIcon("resources/icons/stop.png"));
		_stopB.addActionListener((e) -> {
			this._ficherosB.setEnabled(true);
			this._forceLawsB.setEnabled(true);
			this._viewerB.setEnabled(true);
			this._runB.setEnabled(true);
			this._stopped = true;
		});
		_toolaBar.add(_stopB);
		
		JLabel _stepsL = new JLabel("Steps: ");
		_toolaBar.add(_stepsL);
		_stepsSpinner = new JSpinner();
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
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}

}
