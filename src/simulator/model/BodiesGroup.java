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
		if(bodies.contains(b) || b == null) //comprobar que no existe ning�n otro cuerpo en el
											//grupo con el mismo identificador
			throw new IllegalArgumentException(); //?
		else
			bodies.add(b); 
	}
	
	public void advance(double dt) {
		
		for(Body b : bodies) {
			//1. Llama al metodo resetForce de todos los cuerpos
			b.resetForce();
			//2. Llama al metodo apply de las leyes de fuerza
			law.apply(bodies);
			//3. llama a advance(dt) para cada cuerpo, si dt no es positivo lanza excepcion
			if(dt >= 0)
				b.advance(dt);
			else
				throw new IllegalArgumentException(); //?
		}
		
	}
	
	public JSONObject getState() { //int i?
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
