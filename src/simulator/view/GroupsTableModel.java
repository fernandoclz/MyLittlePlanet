package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

class GroupsTableModel extends AbstractTableModel implements SimulatorObserver {
	String[] _header = { "Id", "Force Laws", "Bodies" };
	List<BodiesGroup> _groups;
	
	GroupsTableModel(Controller ctrl) {
		_groups = new ArrayList<>();
		// TODO registrar this como observador;
		ctrl.addObserver(this);
	}
	
	// TODO el resto de m�todos van aqu� �

	@Override
	public String getColumnName(int i) {
		return _header[i];
	}
	@Override
	public boolean isCellEditable(int i, int j) {
		return false;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return _groups.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return _header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		BodiesGroup g = _groups.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return g.getId();
		case 1:
			return g.getForceLawsInfo();
		case 2:
			String aux = "";
			for(Body cuerpo: g) {
				aux = aux + cuerpo.getId() + " ";
			}
			return aux;
		}
		return g;
	}

	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_groups.clear();
		fireTableStructureChanged();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_groups = new ArrayList<BodiesGroup>();
		
		for(BodiesGroup bg: groups.values()) {

			_groups.add(bg);
		}
		fireTableStructureChanged();
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		_groups.add(g);
		fireTableStructureChanged();
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		fireTableDataChanged();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		fireTableDataChanged();
	}
}
