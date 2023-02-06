package simulator.model;

import java.util.List;
import simulator.model.Body;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{

	Vector2D c;
	double g; //aceleracion
	
	public MovingTowardsFixedPoint (Vector2D c, double g) throws IllegalArgumentException {
		this.c = c;
		this.g = g;
	}

	@Override
	public void apply(List<Body> bs) {
		// TODO Auto-generated method stub
		Vector2D d;
		d = c.minus((bs).getPosition());
		bs.f = d.scale(bs.m * g);
	}
}
