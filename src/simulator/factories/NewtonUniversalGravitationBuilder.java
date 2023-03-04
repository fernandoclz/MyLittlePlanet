package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	public NewtonUniversalGravitationBuilder() {
		// TODO Auto-generated constructor stub
		super("nlug", "law");
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		// TODO Auto-generated method stub
		Double g = 6.67e10-11;
		
		if(data.has("g")) {
			g = data.getDouble("G");
		}
		
		
		return new NewtonUniversalGravitation(g); //mentira, está bien hecho pero es mentira
	}

}
