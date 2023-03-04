package simulator.factories;


import org.json.JSONException;
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
		
		
		String id;
		String gid;
		Vector2D v;
		Vector2D p;
		Double m;
		try {
		id = data.getString("id");
		gid = data.getString("gid");
		if(data.getJSONArray("v").length()== 2)
			v = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
		else {
			throw new IllegalArgumentException(); 
		}
		if(data.getJSONArray("p").length()==2)
			p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		else
			throw new IllegalArgumentException(); 
		m = data.getDouble("m");
		}
		catch(JSONException e) {
			throw new IllegalArgumentException(); 
		}
		return new MovingBody(id, gid, p, v, m);
	}

}
