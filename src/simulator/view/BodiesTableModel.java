package simulator.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	String[] _header = { "Id", "gId", "Mass", "Velocity", "Position", "Force" };
	List<Body> _bodies;
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		// TODO registrar this como observer
		ctrl.addObserver(this);
	}

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
		return _bodies.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return _header.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Body b = _bodies.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return b.getId();
		case 1:
			return b.getgId();
		case 2:
			return b.getMass();
		case 3:
			return b.getVelocity().toString();
		case 4:
			return b.getPosition().toString();
		case 5: 
			return b.getForce().toString();
		}
		return b;
	}

	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		fireTableDataChanged();
	}

	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_bodies.clear();
		fireTableStructureChanged();
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		_bodies = new ArrayList<Body>();
		
		for(BodiesGroup bg: groups.values()) {
			for(Body b: bg) {
				_bodies.add(b);
			}
		}
		fireTableStructureChanged();
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(Map<String, BodiesGroup> groups, Body b) {
		// TODO Auto-generated method stub
		_bodies.add(b);
		fireTableStructureChanged();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(BodiesGroup g) {
		// TODO Auto-generated method stub
		
	}
	
	// TODO el resto de m�todos van aqu�
}
