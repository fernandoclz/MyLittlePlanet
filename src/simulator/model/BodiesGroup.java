package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BodiesGroup {

	//Attributes
	private String id; //id del grupo
	private ForceLaws law; //leyes de fuerza
	private List<Body>bodies; //lista de Cuerpos
	
	//Constructor
	public BodiesGroup (String id, ForceLaws law) throws IllegalArgumentException{
		this.id = id;
		this.law = law;
		bodies = new ArrayList<>();
		//exceptions
	}
	
	//Methods
	public String getId() {
		return this.id;
	}
	
	public void setForceLaws(ForceLaws fl) {
		if(fl != null)
			this.law = fl;
		else
			throw new IllegalArgumentException(); //?
		
	}
	
	public void addBody(Body b) { 
		if(bodies.contains(b) || b == null) //comprobar que no existe ningún otro cuerpo en el
											//grupo con el mismo identificador
			throw new IllegalArgumentException(); //?
		else
			bodies.add(b); 
	}
	
	public void advance(double dt) {
		//1. Llama al método resetForce de todos los cuerpos
		for() {
			
		}
		//2. Llama al método apply de las leyes de fuerza
	}
	/*
	public JSONObject getState() {
		JSONObject obj;
		
		obj.put("id", this.id);
		obj.put("bodies", this.bodies.getState());
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}*/
}
