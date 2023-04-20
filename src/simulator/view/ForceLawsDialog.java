package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.misc.Vector2D;
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
	private JButton _okayB;
	private int info;
	private int _selectedLawsIndex;
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

		JPanel textos = new JPanel(new BorderLayout());

		JLabel text = new JLabel("Select a force law and provide values for the parameters in the ");
		JLabel textBold = new JLabel("Value column ");
		Font f= new Font("Arial", Font.BOLD, 14); // Nombre, Estilo, Tamaño
		textBold.setFont(f);
		JLabel endText = new JLabel("(default values are used for ");
		JLabel endText1= new JLabel("parameters with no value)");

		textos.add(text, BorderLayout.WEST);

		JPanel ajuste = new JPanel (new BorderLayout());

		ajuste.add(textBold, BorderLayout.WEST);
		ajuste.add(endText, BorderLayout.CENTER);
		textos.add(ajuste, BorderLayout.CENTER);
		textos.add(endText1, BorderLayout.SOUTH);

		mainPanel.add(textos);

		_dataTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO hacer editable solo la columna 1
				return column == 1;
			}
		};
		_dataTableModel.setColumnIdentifiers(_headers);

		JTable table = new JTable(_dataTableModel);
		JScrollPane s = new JScrollPane(table);
		updateTable(0);
		mainPanel.add(s);


		JPanel boxes = new JPanel(new FlowLayout());
		JLabel lawL = new JLabel("Force Law:");
		boxes.add(lawL);
		_lawsModel = new DefaultComboBoxModel<>();
		// TODO a�adir la descripci�n de todas las leyes de fuerza a _lawsModel
		for(JSONObject o: _forceLawsInfo) {
			_lawsModel.addElement(o.getString("desc"));
		}

		// TODO crear un combobox que use _lawsModel y a�adirlo al panel
		JComboBox law = new JComboBox(_lawsModel);

		law.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				info = law.getSelectedIndex();
				_selectedLawsIndex = info;
				updateTable(_selectedLawsIndex);
			}

		});

		boxes.add(law);
		JLabel groupL = new JLabel("Group:");
		boxes.add(groupL);
		_groupsModel = new DefaultComboBoxModel<>();
		for(BodiesGroup bg: _group) {
			_groupsModel.addElement(bg.getId());
		}
		// TODO crear un combobox que use _groupsModel y a�adirlo al panel
		JComboBox groups = new JComboBox(_groupsModel);
		boxes.add(groups);


		law.setPreferredSize(new Dimension(330, 35));
		law.setMaximumSize(new Dimension(330, 35));
		groups.setPreferredSize(new Dimension(100, 35));
		groups.setMaximumSize(new Dimension(100, 35));

		mainPanel.add(boxes);
		// TODO crear los botones OK y Cancel y a�adirlos al panel


		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());


		_okayB = new JButton("Okay");

		_okayB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				JSONObject data = getJSONData();

				JSONObject newL = new JSONObject();

				newL.put("type", _forceLawsInfo.get(_selectedLawsIndex).getString("type"));
				newL.put("data", data);

				_ctrl.setForcesLaws(groups.getSelectedItem().toString(), newL);
				setVisible(false);
			}

		});

		_okayB.setPreferredSize(new Dimension(100,30));

		botones.add(_okayB);

		_cancelB = new JButton("Cancel");

		_cancelB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}

		});

		_cancelB.setPreferredSize(new Dimension(100,30));

		botones.add(_cancelB);
		mainPanel.add(botones);
		setPreferredSize(new Dimension(700, 400));


		pack();
		setResizable(false);
		setVisible(false);
	}

	public JSONObject getJSONData() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		String key = "";
		String value;
		String primero = null;
		String segundo = null;
		for(int i = 0; i < _dataTableModel.getRowCount(); i++) {
			key = _dataTableModel.getValueAt(i, 0).toString();
			value = _dataTableModel.getValueAt(i, 1).toString();
			if(value != "") {
				if(key == "c") {
					try {
						String[] partes = value.split("\\,");
						if(partes[0].charAt(0) == '[' && partes[1].charAt(partes[1].length()-1) == ']') {
							primero  = partes[0].substring(1);
							segundo = partes[1].substring(0, partes[1].length()-1);
						}
						else {
							throw new IllegalArgumentException("Faltan corchetes: []");
						}
						Vector2D pos = new Vector2D(Double.parseDouble(primero), Double.parseDouble(segundo));
						data.put(key, pos.toString());
					}catch(Exception e) {
						Utils.showErrorMsg(e.toString());
					}
				}
				else {
					double valor = Double.parseDouble(value);
					data.put(key, valor);
				}
			}
		}
		return data;
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

	public void updateTable(int index) {	
		JSONObject jLaw = _forceLawsInfo.get(index);
		JSONObject data = jLaw.getJSONObject("data");
		int i = 0;

		_dataTableModel.setRowCount(0);
		for(String key: data.keySet()) {
			_dataTableModel.insertRow(i, new String[] {
					key, "", data.getString(key)
			});
			i++;
		}
		_dataTableModel.fireTableDataChanged();
	}
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