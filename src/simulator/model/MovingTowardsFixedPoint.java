package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	Vector2D c; //punto fijo
	double g; //aceleracion
	
	public MovingTowardsFixedPoint (Vector2D c, double g){
		if (c == null || g <= 0) {
			throw new IllegalArgumentException("Constructor mtfp");
		}
		else {
			this.c = c;
			this.g = g;
		}
	}

	@Override
	public void apply(List<Body> bs) {
		Vector2D d;
		for(Body b: bs) {
			d = c.minus(b.getPosition()).direction();
			b.setForce(d.scale(b.getMass()*g));
		}
	}
	
	public String toString() {
		return "Moving towards " + c + " with constant acceleration " + g;
	}
}
