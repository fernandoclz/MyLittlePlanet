package simulator.factories;


import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MovingBody;

public class MovingBodyBuilder extends Builder<Body>{

	public MovingBodyBuilder(){
		super("mv_body", "body");
	}
	public MovingBodyBuilder(String typeTag, String desc) { 
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) { //devuelve una instancia de la clase correspondiente (MovingBody)
		// TODO Auto-generated method stub			 // (una instancia de un subtipo de T)
		
		
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		
		
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		String id = data.getString("id");
		String gid = data.getString("gid");
		Vector2D v = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
		Vector2D p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		Double m = data.getDouble("m");
		
		return new MovingBody(id, gid, v, p, m);
	}

}
