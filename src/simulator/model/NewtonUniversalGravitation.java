package simulator.model;

import java.util.List;
import simulator.model.Body;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation (double G) {
		if(G < 0)
			throw new IllegalArgumentException(); //?
		
		else {
			this.G = G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) { //cambia
		// TODO Auto-generated method stub
		double f = 0;
		double mul = 0;
		for(Body bi: bs) {
			for(Body bj: bs) {
				
				if(bi.getPosition().distanceTo(bj.getPosition()) == 0 && bi != bj){
					
				}
				else if (bi.getPosition().distanceTo(bj.getPosition()) > 0 && bi != bj) {
					mul = G * bi.getMass() * bj.getMass();
					f = mul * 1/(bi.getPosition().distanceTo(bj.getPosition()) * bi.getPosition().distanceTo(bj.getPosition()));
					
				}
				bi.addForce((bi.getPosition().minus(bj.getPosition())).scale(f));
			}
			
		}
	}
	
}
