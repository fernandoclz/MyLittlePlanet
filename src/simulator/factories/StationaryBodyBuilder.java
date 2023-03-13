package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.StationaryBody;

public class StationaryBodyBuilder extends Builder<Body>{

	public StationaryBodyBuilder() { //solo este
		super("st_body", "body");
	}


	@Override
	protected Body createInstance(JSONObject data) { 
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		String id;
		String gid;
		Vector2D p;
		Double m;
		if(data.has("id") && data.has("gid") && data.has("p") && data.has("m") && data.getJSONArray("p").length()==2 ) {
			id = data.getString("id");
			gid = data.getString("gid");
			p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
			m = data.getDouble("m");
			
		}
		
		else {
			throw new IllegalArgumentException(); 
		}
		
		return new StationaryBody(id, gid, p, m);
	}
	@Override
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		return data;
	}

}
