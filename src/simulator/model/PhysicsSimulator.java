package simulator.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	private double t;
	private static double tiempoActual;
	private ForceLaws law;
	private Map<String,BodiesGroup> mapa; //? clave : gid, valor: grupo
	private List<String> listaGid;
	
	public PhysicsSimulator (ForceLaws law, double t) throws IllegalArgumentException{ //public PhysicsSimulator ()
		if(t < 0 || law == null) 
			throw new IllegalArgumentException();

			this.t = t; //this.t = 0
			PhysicsSimulator.tiempoActual  = 0.0;
			this.law = law;
			mapa = new LinkedHashMap<String,BodiesGroup>();
		
	}

	//Methods
	public void advance() {
		
		for(BodiesGroup b: mapa.values()) {
			b.advance(t);
		}
		PhysicsSimulator.tiempoActual += t; 
	}
	
	public void addGroup(String id) { //anade un nuevo grupo con identificador
										//id al mapa de grupos
		if(mapa.containsKey(id)) {
			throw new IllegalArgumentException();
		}
		
		//Si tira la excepcion esto no se ejecuta
		mapa.put(id, new BodiesGroup(id, law));
	}
	
	public void addBody (Body b) {
		
		if(!mapa.containsKey(b.getgId())) {
			throw new IllegalArgumentException();
		}
	}
	
	public void setForceLaws (String id, ForceLaws fl) {
		boolean existe = false;
		
		//Corregido: Excepcion si no existe un grupo con el mismo id
		for(BodiesGroup b: mapa.values()) {
			if(b.getId() == id){
				b.setForceLaws(fl);
				existe = true;
			}
		}
		
		if(!existe) {
			throw new IllegalArgumentException();
		}
	}
	
	public JSONObject getState() {   //corregido
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		obj.put("time", t);
		/*for(BodiesGroup b: mapa.values()) { 
			//LLeno el JSONARRAY
			//arr.put(b.getState());
		}*/
	//	 El orden de los grupos tiene que ser el orden de creaci�n,
	//	 para esto hay que mantener una lista de identificadores (List<String>) de grupos 
	//	 porque no hay orden garantizado para las claves del mapa
		for(String gid: listaGid) {
			arr.put(mapa.get(gid)); //creo, V get(K clave): Devuelve el valor asociado a la clave (teor�a)
		}
		
		obj.put("groups", arr);
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
}
