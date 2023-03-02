package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingBody;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "force");
	}
	public MovingTowardsFixedPointBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ForceLaws createInstance(JSONObject data) {
		// TODO Auto-generated method stub
		for (String key : data.keySet()) {
			if(key == null) 
				throw new IllegalArgumentException(); 
		}	
		
		
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		Vector2D c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
		Double g = data.getDouble("g");
		
		return new MovingTowardsFixedPoint(c, g);
	}

}
