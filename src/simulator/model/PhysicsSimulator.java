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
		//grupo.advance(this.t)
		this.t++;
	}
	
	public void addGroup(String id) {
		
	}
	
	public void addBody (Body b) {
		
	}
	
	public void setForceLaws (String id, ForceLaws f) {
		
	}
	/*
	public JSONObject getState() {
		JSONObject obj;
		
		obj.put("time", this.t);
		obj.put("groups", this.body.getState());
		return obj;
	}*/
	/*
	public String toString() {
		return getState().toString();
	}*/
}
