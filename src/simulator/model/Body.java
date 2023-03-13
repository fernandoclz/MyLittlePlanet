package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

/*
 * Un objeto de tipo Body contiene
(como atributos protected) un identificador id (String), un identificador de su grupo gid (String), un vector de velocidad ~v, un vector de
fuerza ~f, un vector de posici�n ~p y una masa m (double).
 */
public abstract class Body {
	protected String id;
	protected String gid;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected double m;
	
	protected Body (String id, String gid, Vector2D p, Vector2D v, double m){ 

			
		if(id != null && gid != null && v != null && p != null) {
			if(id.trim().length()>0)
				this.id = id;
			else
				throw new IllegalArgumentException("Constructor body, id");
			if(gid.trim().length()>0)
				this.gid = gid;
			else
				throw new IllegalArgumentException("Constructor body, gid");
			this.v = v;
			this.f = new Vector2D();
			this.p = p;
			if(m > 0)
				this.m = m;
			else
				throw new IllegalArgumentException("Constructor body, m");
		}
		else
			throw new IllegalArgumentException("Constructor body, parametro null");
		
		
	}
 
	public String getId() {  //devuelve el identificador del cuerpo
		return id;		
	}
	
	public String getgId() {		//devuelve el identificador del grupo al que pertenece el cuerpo.
		return gid;    
	}
	
	public Vector2D getVelocity() {   // devuelve el vector de velocidad.
		return v;		
	}
	
	public void setVelocity(Vector2D v) {
		this.v = v;
	}
	
	public Vector2D getForce(){   // devuelve el vector de fuerza.
		return f;			
	}
	
	public void setForce(Vector2D f) {
		this.f = f;
	}
	
	public Vector2D getPosition() {   // devuelve el vector de posici�n.
		return p;		
	}
	
	public void setPosition(Vector2D p) {
		this.p = p;
	}
	
	public double getMass() {   // devuelve la masa del cuerpo.
		return m;			
	}
	
	void addForce(Vector2D f) {// a�ade la fuerza f al vector de fuerza del cuerpo (usando el m�todo plus de la clase Vector2D).
		this.f = this.f.plus(f);
	}
	
	void resetForce(){   
		f = new Vector2D();				 // pone el valor del vector de fuerza a (0, 0) 
	}
	
	abstract void advance(double dt);  //mueve el cuerpo durante dt segundos (las implementaciones est�n el las sub-clases).
		
	public JSONObject getState() {   //devuelve la siguiente informacion del cuerpo en formato JSON (como JSONObject): {�id": id, "m": m, "p": ~p, "v": ~v, "f": ~f}
		
		JSONObject data = new JSONObject();

		data.put("id", id);
		data.put("m", m);
		data.put("p", p.asJSONArray());
		data.put("v", v.asJSONArray());
		data.put("f", f.asJSONArray());
		
		return data;
	 }
	
	public String toString() {
		return getState().toString(); //devuelve getState().toString().
	}
	
}
