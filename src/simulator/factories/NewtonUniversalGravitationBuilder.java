package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	public NewtonUniversalGravitationBuilder() {
		// TODO Auto-generated constructor stub
		super(null, null);
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

}
