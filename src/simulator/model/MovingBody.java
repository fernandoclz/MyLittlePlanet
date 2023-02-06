package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{

	public MovingBody(String id, String gid, Vector2D v, Vector2D p, double m) throws IllegalArgumentException {
		super(id, gid, v, p, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	void advance(double dt) {
		// TODO Auto-generated method stub
	
		Vector2D a;
		
		if(m != 0) {
			
			a = this.f.scale(1/this.m);
		}
		else
			a = new Vector2D();
		 // position a ~p + ~v � t + 1/2 � ~a �t^2 y la velocidad a ~v + ~a � t
		
		this.p = this.p.plus(this.v.scale(dt).plus(a.scale(dt*dt*1/2)));
		this.v = this.v.plus(a.scale(dt));
	
	}

}
