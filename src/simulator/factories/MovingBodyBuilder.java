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


	@Override
	protected Body createInstance(JSONObject data) { //devuelve una instancia de la clase correspondiente (MovingBody)
		// TODO Auto-generated method stub			 // (una instancia de un subtipo de T)	
		
		
		String id;
		String gid;
		Vector2D v;
		Vector2D p;
		Double m;
		
		if(data.has("id") && data.has("gid") && data.has("v") && data.has("p") && data.has("m") && data.getJSONArray("v").length()== 2 && data.getJSONArray("p").length()==2 ) {
			id = data.getString("id");
			gid = data.getString("gid");
			v = new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
			p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
			m = data.getDouble("m");
			
		}
		
		else {
			throw new IllegalArgumentException(); 
		}
		return new MovingBody(id, gid, p, v, m);
	}
	
	
	
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		return data;
	}

}
