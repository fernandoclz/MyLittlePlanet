package simulator.model;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

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
		
		for(BodiesGroup b: mapa.values()) {
			//LLeno el JSONARRAY
			b.advance(t);
		}
		this.t++;
	}
	
	public void addGroup(String id) { //añade un nuevo grupo con identificador
										//id al mapa de grupos
		mapa.put(id, new BodiesGroup());
		// faltan cosas
	}
	
	public void addBody (Body b) {
		
		for(BodiesGroup bg: mapa.values()) {
			//Corregido: Excepción si no existe un grupo con el mismo id
			if(bg.getId() == b.getId()) {
				bg.addBody(b);
			}
			else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	public void setForceLaws (String id, ForceLaws fl) {
		
	}
	
	public JSONObject getState() {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		for(BodiesGroup b: mapa.values()) {
			//LLeno el JSONARRAY
			arr.put(b);
		}
		
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
}
