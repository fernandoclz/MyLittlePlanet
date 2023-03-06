package simulator.model;

import java.util.List;
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
		double f = 0;
		double mul = 0;
		Vector2D dir;
		for(Body bi: bs) {
			Vector2D force = new Vector2D();
			for(Body bj: bs) {
				f = 0;
				if (bi.getPosition().distanceTo(bj.getPosition()) > 0 && bi != bj) {
					mul = G * bi.getMass() * bj.getMass();
					f = mul/(bi.getPosition().distanceTo(bj.getPosition()) * bi.getPosition().distanceTo(bj.getPosition()));
					
				}
				dir = bj.getPosition().minus(bi.getPosition()).direction();
				force = force.plus(dir.scale(f));
				
			}
			bi.setForce(force);
		}
	}
	
	public String toString() {
		return "Newton's Universal Gravitation with G=" + G;
	}
	
}
