package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{

	public MovingBodyBuilder(){
		super(null, null);
	}
	public MovingBodyBuilder(String typeTag, String desc) { 
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) { //devuelve una instancia de la clase correspondiente (MovingBody)
		// TODO Auto-generated method stub			 // (una instancia de un subtipo de T)
		//data contiene type(string) y data(JSON) 
		boolean excepcion = false;
		/* Acceder a un JSON anidado
		JSONObject jo2 = jo.getJSONObject("e");
		for (String key : jo2.keySet()) {
			System.out.println("-> " + key);
		}*/
		JSONObject data2 = data.getJSONObject("data"); //comprobamos si el data interior tiene alguna clave que sea null
		for (String key : data2.keySet()) {
			if(key == null) excepcion = true; //se comprueba que el key no es null o que el valor del key no es null?
		}	
		
		if(data.has("type") && excepcion ) {
			throw new IllegalArgumentException(); 
		}
		
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		return new MovingBody(data2.getString("id"), data2.getString("gid"), data2.get("v"), data2.get("p"), data2.getDouble("m"));
	}

}
