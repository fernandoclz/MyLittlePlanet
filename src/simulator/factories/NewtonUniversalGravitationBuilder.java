package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}

	public NewtonUniversalGravitationBuilder() {
		super("nlug", "law");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		Double g = 6.67E-11;
		
		if(data.has("G")) {
			g = data.getDouble("G");
		}
		
		
		return new NewtonUniversalGravitation(g); 
	}

}
