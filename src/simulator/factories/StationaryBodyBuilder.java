package simulator.factories;

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
	}

	@Override
	protected Body createInstance(JSONObject data) throws IllegalArgumentException{
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		String id;
		String gid;
		Vector2D p;
		Double m;
		try {													//Si alguna key es null o no contiene un tipo aceptado, tira JSONException
		id = data.getString("id");
		gid = data.getString("gid");
		if(data.getJSONArray("p").length()==2)
			p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		else
			throw new IllegalArgumentException(); 
		m = data.getDouble("m");
		}
		catch(JSONException e) {
			throw new IllegalArgumentException(); 
		}
		
		return new StationaryBody(id, gid, p, m);
	}
	
	
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		return data;
	}

}
