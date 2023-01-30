package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector2D;

/*
 * Un objeto de tipo Body contiene
(como atributos protected) un identificador id (String), un identificador de su grupo gid (String), un vector de velocidad ~v, un vector de
fuerza ~f, un vector de posición ~p y una masa m (double).
 */
public abstract class Body {
	protected String id;
	protected String gid;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected double m;
	
	public Body (String id, String gid, Vector2D v, Vector2D p, double m) throws IllegalArgumentException{
		this.id = id;
		this.gid = gid;
		this.v = v;
		this.f = new Vector2D();
		this.p = p;
		this.m = m;
	}
 
	public String getId() {  //devuelve el identificador del cuerpo
		return id;		
	}
	public String getGid() {		//devuelve el identificador del grupo al que pertenece el cuerpo.
		return gid;    
	}
	public Vector2D getVelocity() {   // devuelve el vector de velocidad.
		return v;		
	}
	public Vector2D getForce(){   // devuelve el vector de fuerza.
		return f;			
	}
	public Vector2D getPosition() {   // devuelve el vector de posición.
		return p;		
	}
	public double getMass() {   // devuelve la masa del cuerpo.
		return m;			
	}
	
	protected void addForce(Vector2D f) {// añade la fuerza f al vector de fuerza del cuerpo (usando el método plus de la clase Vector2D).
		
	}
	
	protected void resetForce(){   // pone el valor del vector de fuerza a (0, 0).
		
	}
	abstract void advance(double dt);  //mueve el cuerpo durante dt segundos (las implementaciones están el las sub-clases).
		
	public JSONObject getState() {
		return null; //devuelve la siguiente información del cuerpo en formato JSON (como JSONObject): {“id": id, "m": m, "p": ~p, "v": ~v, "f": ~f}
	
	 }
	public String toString() {
		return getState().toString(); //devuelve getState().toString().
	}
}
