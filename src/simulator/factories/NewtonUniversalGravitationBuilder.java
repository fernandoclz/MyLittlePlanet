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
		return new NewtonUniversalGravitation(data.getDouble("G")); //mentira, está bien hecho pero es mentira
	}

}
