package simulator.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

class ForceLawsDialog extends JDialog implements SimulatorObserver {
	private DefaultComboBoxModel<String> _lawsModel;
	private DefaultComboBoxModel<String> _groupsModel;
	private DefaultTableModel _dataTableModel;
	private Controller _ctrl;
	private List<JSONObject> _forceLawsInfo;
	private String[] _headers = { "Key", "Value", "Description" };
	// TODO en caso de ser necesario, a�adir los atributos aqu�
	private List<BodiesGroup> _group;
	private JButton _cancelB;
	ForceLawsDialog(Frame parent, Controller ctrl) {
		super(parent, true);
		_ctrl = ctrl;
		_group = new ArrayList<BodiesGroup>();
		initGUI();
		// TODO registrar this como observer;
		ctrl.addObserver(this);
	}
	private void initGUI() {
		setTitle("Force Laws Selection");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		// _forceLawsInfo se usar� para establecer la informaci�n en la tabla
		_forceLawsInfo = _ctrl.getForceLawsInfo();
		// TODO crear un JTable que use _dataTableModel, y a�adirla al panel
		JTable table = new JTable(_dataTableModel);
		
		mainPanel.add(table);
		_dataTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO hacer editable solo la columna 1
				return column == 1;
			}
		};
		_dataTableModel.setColumnIdentifiers(_headers);
		_lawsModel = new DefaultComboBoxModel<>();
		// TODO a�adir la descripci�n de todas las leyes de fuerza a _lawsModel
		for(JSONObject o: _forceLawsInfo) {
			_lawsModel.addElement(o.getString("desc"));
		}
		
		// TODO crear un combobox que use _lawsModel y a�adirlo al panel
		JComboBox law = new JComboBox(_lawsModel);
		mainPanel.add(law);
		_groupsModel = new DefaultComboBoxModel<>();
		for(BodiesGroup bg: _group) {
			_groupsModel.addElement(bg.getId());
		}
		// TODO crear un combobox que use _groupsModel y a�adirlo al panel
		JComboBox groups = new JComboBox(_groupsModel);
		mainPanel.add(groups);
		
		
		law.setPreferredSize(new Dimension(330, 35));
		law.setMaximumSize(new Dimension(330, 35));
		groups.setPreferredSize(new Dimension(100, 35));
		groups.setMaximumSize(new Dimension(100, 35));
		// TODO crear los botones OK y Cancel y a�adirlos al panel
		_cancelB = new JButton();
		
		
		
		mainPanel.add(_cancelB);
		setPreferredSize(new Dimension(700, 400));
		
		
		pack();
		setResizable(false);
		setVisible(false);
	}
	public void open() {
		if (_groupsModel.getSize() == 0)
			return;
		// TODO Establecer la posici�n de la ventana de di�logo de tal manera que se
		// abra en el centro de la ventana principal
		pack();
		setVisible(true);
		return;
	}
	// TODO el resto de m�todos van aqu�
	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_group = new ArrayList<BodiesGroup>();
		
		for(BodiesGroup bg: groups.values()) {

			_group.add(bg);
		}
		
		initGUI();
	}
	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_group = new ArrayList<BodiesGroup>();
		
		for(BodiesGroup bg: groups.values()) {

			_group.add(bg);
		}
		
		initGUI();
	}
	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		_group = new ArrayList<BodiesGroup>();
		
		for(BodiesGroup bg: groups.values()) {

			_group.add(bg);
		}
		initGUI();
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