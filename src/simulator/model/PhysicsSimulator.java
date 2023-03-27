package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator implements Observable<SimulatorObserver>{

	private double t;
	private static double tiempoActual;
	private ForceLaws law;
	private Map<String,BodiesGroup> mapa; 
	private List<String> listaGid;
	private List<SimulatorObserver> listaObs;
	private Map<String, BodiesGroup> _groupsRO;

	public PhysicsSimulator (ForceLaws law, double t){ 
		if(t < 0 || law == null) 
			throw new IllegalArgumentException("PS constructor, parametros");

			this.t = t; //this.t = 0
			PhysicsSimulator.tiempoActual  = 0.0;
			this.law = law;
			mapa = new LinkedHashMap<String,BodiesGroup>();
			listaGid = new ArrayList<>();
			listaObs = new ArrayList<SimulatorObserver>();
			_groupsRO = Collections.unmodifiableMap(mapa);

	}

	//Methods
	public void advance() {
		
		for(BodiesGroup b: mapa.values()) {
			b.advance(t);
		}
		PhysicsSimulator.tiempoActual += t; 
		
		for(SimulatorObserver o : listaObs) {
			o.onAdvance(_groupsRO, tiempoActual);
		}
	}
	
	public void addGroup(String id) { //anade un nuevo grupo con identificador
										//id al mapa de grupos
		if(mapa.containsKey(id)) {
			throw new IllegalArgumentException("PS addGroup, no contiene id");
		}
		

		mapa.put(id, new BodiesGroup(id, law));
		listaGid.add(id);
		
		for(SimulatorObserver o : listaObs) {
			o.onGroupAdded(_groupsRO, mapa.get(id));
		}
	}
	
	public void addBody (Body b) {
		
		if(!mapa.containsKey(b.getgId())) {
			throw new IllegalArgumentException("");
		}
		
		mapa.get(b.getgId()).addBody(b);
		
		for(SimulatorObserver o : listaObs) {
			o.onBodyAdded(_groupsRO, b);
		}
	}
	
	public void setForceLaws (String id, ForceLaws fl) {
		boolean existe = false;
		
		
		if(mapa.containsKey(id)){
			mapa.put(id, mapa.get(id)).setForceLaws(fl);;
			existe = true;
		}
		
		if(!existe) {
			throw new IllegalArgumentException();
		}
		
		for(SimulatorObserver o : listaObs) {
			o.onForceLawsChanged(mapa.get(id));
		}
	}
	
	public JSONObject getState() {  
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		
		obj.put("time", tiempoActual);

		for(String gid: listaGid) {
			arr.put(mapa.get(gid).getState()); 
		}
		
		obj.put("groups", arr);
		
		return obj;
	}
	
	public String toString() {
		return getState().toString();
	}
	
	public void reset() {
		mapa.clear();
		listaGid.clear();
		PhysicsSimulator.tiempoActual  = 0.0;
		for(SimulatorObserver o : listaObs) {
			o.onReset(_groupsRO, tiempoActual, t);
		}
	}
	
	public void setDeltaTime(double dt) {
		if(dt <= 0)
			throw new IllegalArgumentException();
		
		PhysicsSimulator.tiempoActual = dt;
		
		for(SimulatorObserver o : listaObs) {
			o.onDeltaTimeChanged(dt);
		}
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!this.listaObs.contains(o))
			this.listaObs.add(o);
		
		o.onRegister(_groupsRO, tiempoActual, t);
		
	}
	
	public void removeObserver(SimulatorObserver o) {
		if(this.listaObs.contains(o)) 
			this.listaObs.remove(o);
	}
}
