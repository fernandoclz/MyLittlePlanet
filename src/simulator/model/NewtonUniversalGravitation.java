package simulator.model;

import java.util.List;
import simulator.model.Body;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation (double G) {
		if(G <= 0) {
			throw new IllegalArgumentException();
			 //lanzar una excepción de tipo IllegalArgumentException
													// si el valor de G no es positivo
		}
		this.G = G;
	}
	
	@Override
	public void apply(List<Body> bs) { //cambia
		// TODO Auto-generated method stub
		double f = 0;
		double mul = 0;
		Vector2D dir;
		for(Body bi: bs) {
			for(Body bj: bs) {
				f = 0;
				if(bi.getPosition().distanceTo(bj.getPosition()) == 0 && bi != bj){
					
				}
				else if (bi.getPosition().distanceTo(bj.getPosition()) > 0 && bi != bj) {
					mul = G * bi.getMass() * bj.getMass();
					f = mul/(bi.getPosition().distanceTo(bj.getPosition()) * bi.getPosition().distanceTo(bj.getPosition()));
					
				}
				dir = bj.getPosition().minus(bi.getPosition()).direction();
				bi.addForce(dir.scale(f));
				
			}
		}
	}
	
}
