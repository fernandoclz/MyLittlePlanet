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
		
		Vector2D c ;
		Double g ;
		// TODO Auto-generated method stub
		if(data.get("c") != null) {
			c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
		}
		else {
			c = new Vector2D();
		}
		if(data.get("g") != null) {
			g = data.getDouble("g");
		}
		else {
			g = 9.81;
		}
		//MovingBody(String id, String gid, Vector2D v, Vector2D p, double m)
		
		
		return new MovingTowardsFixedPoint(c, g);
	}

}
