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
		double a1, a2;
	
		Vector2D a;
		
		if(m != 0) {
			a1 = this.f.getX()/m;
			a2 = this.f.getY()/m;
			a = new Vector2D(a1,a2);
		}
		else
			a = new Vector2D();
		 // posici�n a ~p + ~v � t + 1/2 � ~a �t^2 y la velocidad a ~v + ~a � t
		
		double p1, p2, v1, v2;
		
		p1 = this.p.getX() + this.v.getX() * dt + (1/2)* a.getX() * dt*2;
		p2 = this.p.getY() + this.v.getY() * dt + (1/2)* a.getY() * dt*2;
		
		this.p = new Vector2D(p1,p2);
		
		
		v1 = this.v.getX() + a.getX() * dt;
		v2 = this.v.getY() + a.getY() * dt;
		
		this.v = new Vector2D(v1,v2);
	}

}
