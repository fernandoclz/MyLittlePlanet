package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;

public class NoForceBuilder extends Builder<ForceLaws>{

	
	public NoForceBuilder(){
		super(null, null);
	}
	public NoForceBuilder(String typeTag, String desc) {
		super(typeTag, desc);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

}
