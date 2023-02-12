package simulator.model;

import java.util.Map;

public class PhysicsSimulator {

	private double t;
	private ForceLaws law;
	//map
	
	public PhysicsSimulator (double t, ForceLaws law) {
		this.t = t;
		this.law = law;
	}
	
	//Methods
	public void advance() {
		
		this.t++;
	}
}
