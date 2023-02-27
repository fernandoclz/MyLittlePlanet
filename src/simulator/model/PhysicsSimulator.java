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
		this.t++;  //problema, tienen que ser segundos reales, aqui añadimos 1 por ciclo
	}
	
	public void addGroup(String id) { //añade un nuevo grupo con identificador
										//id al mapa de grupos
		for(BodiesGroup b: mapa.values()) {
			//LLeno el JSONARRAY
			if(id == b.getId()) {
				throw new IllegalArgumentException();
			}
		}
		
		//Si tira la excepción esto no se ejecuta
		mapa.put(id, new BodiesGroup());
		// anhadir leyes físicas
		
	}
	
	public void addBody (Body b) {
		
		boolean existe = false;
		
		for(BodiesGroup bg: mapa.values()) {
			//Corregido: Excepción si no existe un grupo con el mismo id
			
			if(bg.getId() == b.getId()) {
				bg.addBody(b);
				existe = true;
			}
		}
		
		if(!existe) {
			throw new IllegalArgumentException();
		}
	}
	
	public void setForceLaws (String id, ForceLaws fl) {
		boolean existe = false;
		
		//Corregido: Excepción si no existe un grupo con el mismo id
		for(BodiesGroup b: mapa.values()) {
			//LLeno el JSONARRAY
			if(b.getId() == id){
				b.setForceLaws(fl);
				existe = true;
			}
		}
		
		if(!existe) {
			throw new IllegalArgumentException();
		}
	}
	
	public JSONObject getState() {   //Esto no está bien
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
