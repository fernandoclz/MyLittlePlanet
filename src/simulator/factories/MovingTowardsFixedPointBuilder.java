package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "force");
	}
	public MovingTowardsFixedPointBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}
	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		Vector2D c ;
		Double g ;
		if(data.has("c")) {
			c = new Vector2D(data.getJSONArray("c").getDouble(0), data.getJSONArray("c").getDouble(1));
		}
		else {
			c = new Vector2D();
		}
		if(data.has("g")) {
			g = data.getDouble("g");
		}
		else {
			g = 9.81;
		}		
		
		return new MovingTowardsFixedPoint(c, g);
	}

}
