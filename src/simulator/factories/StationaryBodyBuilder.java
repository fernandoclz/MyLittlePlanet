package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.StationaryBody;

public class StationaryBodyBuilder extends Builder<Body>{

	public StationaryBodyBuilder() {
		super("st_body", "body");
	}
	public StationaryBodyBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		String id;
		String gid;
		JSONArray pAux;
		Vector2D p;
		Double m;
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		try {
		id = data.getString("id");
		gid = data.getString("gid");
		pAux = data.getJSONArray("p");
		p = new Vector2D(pAux.getDouble(0), pAux.getDouble(1));
		m = data.getDouble("m");
		}
		catch(JSONException e) {
			throw new IllegalArgumentException(); 
		}
		
		return new StationaryBody(id, gid, p, m);
	}

}
