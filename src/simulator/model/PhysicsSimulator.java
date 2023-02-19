package simulator.model;

import java.util.Map;

public class PhysicsSimulator {

	private double t;
	private ForceLaws law;
	private Map<String,BodiesGroup> mapa; //? clave : gid, valor: grupo
	
	public PhysicsSimulator (double t, ForceLaws law) { //public PhysicsSimulator ()
		if(t < 0 || law == null) {
			throw new IllegalArgumentException();
		}
		else {
			this.t = t; //this.t = 0
			this.law = law;
		}
	}
	
	//Methods
	public void advance() {
		//grupo.advance(this.t)
		this.t++;
	}
	
	public void addGroup(String id) { //añade un nuevo grupo con identificador
										//id al mapa de grupos
	
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
