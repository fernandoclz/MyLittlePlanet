package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class BodiesGroup implements Iterable<Body>{

	//Attributes
	private String id; //id del grupo
	private ForceLaws law; //leyes de fuerza
	private List<Body>bodies; //lista de Cuerpos
	List<Body> _bodiesRO;
	
	//Constructor
	
	public BodiesGroup() {
		
	}
	
	public BodiesGroup (String id, ForceLaws law) throws IllegalArgumentException{	
		//(1) cualquier parámetro es null, (2) el identificador no incluye al menos un carćter que no sea espacio en blanco – usa s.trim().length()>0.
		
		if(id == null || law == null) {
			throw new IllegalArgumentException("Constructor bodies group, parametro null");
		}
		else if(id.trim().length() == 0) {
			throw new IllegalArgumentException("Constructor bg, id");
		}
		
		this.id = id;
		this.law = law;
		bodies = new ArrayList<>();
		_bodiesRO = Collections.unmodifiableList(bodies);
	}
	
	//Methods
	public String getId() {
		return this.id;
	}
	
	public void setForceLaws(ForceLaws fl) {
		if(fl != null)
			this.law = fl;
		else
			throw new IllegalArgumentException("bg setForceLaws, fl = null");
		
	}
	
	public void addBody(Body b){ 
		if(b == null) { //comprobar que no existe ningun otro cuerpo en el
											//grupo con el mismo identificador
			throw new IllegalArgumentException("bg addBody, b = null");
		}
		for(Body bd : bodies) {
			if(bd.getId() == b.getId()) {
				throw new IllegalArgumentException("bg addBody, body ya existe"); 
			}
		}
			bodies.add(b); 
	}
	
	public void advance(double dt) {

		if(dt <= 0) {
			throw new IllegalArgumentException("bg, advance"); 
		}
		for(Body b : bodies) {
			//1. Llama al metodo resetForce de todos los cuerpos
			b.resetForce();
		}
			//2. Llama al metodo apply de las leyes de fuerza
		law.apply(bodies);
			//3. llama a advance(dt) para cada cuerpo, si dt no es positivo lanza excepcion
		for(Body b : bodies) {

			if(dt > 0)
				b.advance(dt);
		}
		
	}
	
	public JSONObject getState() { 
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		obj.put("id", this.id);
		for(Body b: bodies) {
			//LLeno el JSONARRAY
			arr.put(b.getState());
		}
		obj.put("bodies", arr);
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
	
	public String getForceLawsInfo(){
		return law.toString();
	}

	@Override
	public Iterator<Body> iterator() {
		// TODO Auto-generated method stub
		return _bodiesRO.iterator();
	}
}
