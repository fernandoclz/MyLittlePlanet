package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{

	public MovingTowardsFixedPointBuilder() {
		super("mtfp", "Moving towards a fixed point");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		
		Vector2D c ;
		Double g ;
		if(data.has("c")) {
			String valor = data.get("c").toString();
			String partes[] = valor.split("\\,");
			String primero  = partes[0].substring(1);
			String segundo = partes[1].substring(0, partes[1].length()-1);
			c = new Vector2D(Double.parseDouble(primero), Double.parseDouble(segundo)); 
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
	
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		data.put("c", "the point towards which bodies move (e.g, [100.0,50.0])");
		data.put("g", "the length of the acceleration vector (a number)");

		return data;
	}
}
