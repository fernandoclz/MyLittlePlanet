package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super("nlug", "law");
		// TODO Auto-generated constructor stub
	}

	public NewtonUniversalGravitationBuilder() {
		// TODO Auto-generated constructor stub
		super(null, null);
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		// TODO Auto-generated method stub
		Double g = 6.67e10-11;
		
		if(data.get("G") != null) {
			g = data.getDouble("G");
		}
		
		
		return new NewtonUniversalGravitation(g); //mentira, está bien hecho pero es mentira
	}

}
