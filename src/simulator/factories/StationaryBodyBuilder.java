package simulator.factories;

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
	protected Body createInstance(JSONObject data) {
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		
		
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		String id = data.getString("id");
		String gid = data.getString("gid");
		Vector2D p = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		Double m = data.getDouble("m");
		
		return new StationaryBody(id, gid, p, m);
	}

}
