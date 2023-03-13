package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{


	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Newton's law of universal gravitation");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		Double g = 6.67E-11;
		
		if(data.has("G")) {
			g = data.getDouble("G");
		}
		
		
		return new NewtonUniversalGravitation(g); 
	}
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		data.put("G", "the gravitational constant (a number)");
		
		return data;
	}
}
