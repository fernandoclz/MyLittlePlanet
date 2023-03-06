package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	
	public NoForceBuilder(){
		super("nf", "No force");
	}
	public NoForceBuilder(String typeTag, String desc) {
		super(typeTag, desc);
	}

	@Override
	protected ForceLaws createInstance(JSONObject data) {
		return new NoForce();
	}
	protected JSONObject getData() {
		JSONObject data = new JSONObject();
		
		return data;
	}
}
