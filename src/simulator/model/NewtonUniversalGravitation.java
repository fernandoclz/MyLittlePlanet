package simulator.model;

import java.util.List;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation (double G) {
		if(G < 0)
			throw new IllegalArgumentException();
		
		else {
			this.G = G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) {
		// TODO Auto-generated method stub
		
	}
	
}
