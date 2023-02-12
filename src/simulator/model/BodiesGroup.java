package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class BodiesGroup {

	//Attributes
	private String id;
	private ForceLaws law;
	private List<Body>body;
	
	//Constructor
	public BodiesGroup (String id, ForceLaws law) throws IllegalArgumentException{
		this.id = id;
		this.law = law;
		//exceptions
	}
	
	//Methods
	public String getId() {
		return this.id;
	}
	
	public void setForceLaws(ForceLaws fl) {
		if(fl != null)
			this.law = fl;
		/*else
			throws IllegalArgumentException;
		*/
	}
	
	public void addBody(Body b) { //falta comprobar cosas
		body.add(b); //metodo de la coleccion
	}
	
	public void advance(double dt) {
		
	}
	/*
	public JSONObject getState() {
		JSONObject obj;
		
		obj.put("id", this.id);
		obj.put("bodies", this.body.getState());
		return obj;
	}*/
	/*
	public String toString() {
		return getState().toString();
	}*/
}
