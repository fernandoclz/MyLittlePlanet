package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import simulator.control.Controller;
import simulator.model.BodiesGroup;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	
	// TODO Añadir los atributos necesarios, si hace falta …
	JLabel dt;
	JLabel g;
	
	StatusBar(Controller ctrl) {
		initGUI();
		// TODO registrar this como observador
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		// TODO Crear una etiqueta de tiempo y añadirla al panel
		JLabel labelT = new JLabel("Time: ");
		dt = new JLabel("0");
		this.add(labelT);
		this.add(dt);
		
		JSeparator s = new JSeparator(JSeparator.VERTICAL);
		s.setPreferredSize(new Dimension(10, 20));
		this.add(s);
		
		// TODO Crear la etiqueta de número de grupos y añadirla al panel
		JLabel labelG = new JLabel("Groups: ");
		g = new JLabel(" ");
		this.add(labelG);
		this.add(g);
		// TODO Utilizar el siguiente código para añadir un separador vertical
		//
		
		JSeparator s1 = new JSeparator(JSeparator.VERTICAL);
		s1.setPreferredSize(new Dimension(10, 20));
		this.add(s1);
	}

	@Override
	public void onAdvance(Map<String, BodiesGroup> groups, double time) {
		// TODO Auto-generated method stub
		this.dt.setText("" + time);
	}

	// TODO el resto de métodos van aquí…
	
	@Override
	public void onReset(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		this.g.setText("" + groups.size());
		this.dt.setText("" + time);
	}

	@Override
	public void onRegister(Map<String, BodiesGroup> groups, double time, double dt) {
		// TODO Auto-generated method stub
		this.g.setText("" + groups.size());
		this.dt.setText("" + time);
	}

	@Override
	public void onGroupAdded(Map<String, BodiesGroup> groups, BodiesGroup g) {
		// TODO Auto-generated method stub
		this.g.setText("" + groups.size());
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
