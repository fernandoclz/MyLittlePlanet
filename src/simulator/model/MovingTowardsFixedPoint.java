package simulator.model;

import java.util.List;
import simulator.model.Body;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	Vector2D c; //punto fijo
	double g; //aceleracion
	
	public MovingTowardsFixedPoint (Vector2D c, double g){
		if (c == null || g <= 0) {
			throw new IllegalArgumentException();
		}
		else {
			this.c = c;
			this.g = g;
		}
	}

	@Override
	public void apply(List<Body> bs) {
		// TODO Auto-generated method stub
		Vector2D d;
		for(Body b: bs) {
			d = c.minus(b.getPosition());
			b.f = d.scale(b.getMass()*g);
			System.out.println(b.f);
		}
	}
}
