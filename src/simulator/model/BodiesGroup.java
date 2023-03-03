package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodiesGroup {

	//Attributes
	private String id; //id del grupo
	private ForceLaws law; //leyes de fuerza
	private List<Body>bodies; //lista de Cuerpos
	
	//Constructor
	
	public BodiesGroup() {
		
	}
	
	public BodiesGroup (String id, ForceLaws law) throws IllegalArgumentException{	
		//(1) cualquier parámetro es null, (2) el identificador no incluye al menos un carćter que no sea espacio en blanco – usa s.trim().length()>0.
		
		if(id == null || law == null) {
			throw new IllegalArgumentException();
		}
		else if(id.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		
		this.id = id;
		this.law = law;
		bodies = new ArrayList<>();
		//falta exceptions
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
	
	public void addBody(Body b) throws IllegalArgumentException{ 
		if(b == null) { //comprobar que no existe ningun otro cuerpo en el
											//grupo con el mismo identificador
			throw new IllegalArgumentException(); //?
		}
		for(Body bd : bodies) {
			if(bd.getId() == b.getId()) {
				throw new IllegalArgumentException(); //?
			}
		}
			bodies.add(b); 
	}
	
	public void advance(double dt) throws IllegalArgumentException{
		
		/*
		 if(dt < 0)
		 	throw new IllegalArgumentException(); //?
		 */
		for(Body b : bodies) {
			//1. Llama al metodo resetForce de todos los cuerpos
			b.resetForce();
			//2. Llama al metodo apply de las leyes de fuerza
			law.apply(bodies);
			//3. llama a advance(dt) para cada cuerpo, si dt no es positivo lanza excepcion
			if(dt > 0)
				b.advance(dt);
			else
				throw new IllegalArgumentException(); //?
			//quito el if y el else y pongo b.advance(dt);
		}
		
	}
	
	public JSONObject getState() { 
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		obj.put("id", this.id);
		for(Body b: bodies) {
			//LLeno el JSONARRAY
			arr.put(b);
		}
		obj.put("bodies", arr);
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
}
