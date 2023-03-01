package simulator.model;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	private double t;
	private ForceLaws law;
	private Map<String,BodiesGroup> mapa; //? clave : gid, valor: grupo
	private List<String> listaGid;
	
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
		this.t++;  //problema, tienen que ser segundos reales, aqui anadimos 1 por ciclo
	}
	
	public void addGroup(String id) { //anade un nuevo grupo con identificador
										//id al mapa de grupos
		for(BodiesGroup b: mapa.values()) {
			//LLeno el JSONARRAY
			if(id == b.getId()) {
				throw new IllegalArgumentException();
			}
		}
		
		//Si tira la excepcion esto no se ejecuta
		mapa.put(id, new BodiesGroup());
		// anhadir leyes fisicas
		setForceLaws(id, law);
	}
	
	public void addBody (Body b) {
		
		boolean existe = false;
		
		for(BodiesGroup bg: mapa.values()) {
			//Corregido: Excepcion si no existe un grupo con el mismo id
			
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
	//	 El orden de los grupos tiene que ser el orden de creación,
	//	 para esto hay que mantener una lista de identificadores (List<String>) de grupos 
	//	 porque no hay orden garantizado para las claves del mapa
		for(String gid: listaGid) {
			arr.put(mapa.get(gid)); //creo, V get(K clave): Devuelve el valor asociado a la clave (teoría)
		}
		
		obj.put("groups", arr);
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
}
