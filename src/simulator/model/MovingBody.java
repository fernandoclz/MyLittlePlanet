package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{

	protected Vector2D a; //aceleracion //creo que iria como atributo
	
	public MovingBody(String id, String gid, Vector2D v, Vector2D p, double m) throws IllegalArgumentException {
		super(id, gid, v, p, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	void advance(double dt) {
		// TODO Auto-generated method stub
	
		//calculo aceleracion
		if(m != 0) {
			//a = f/m
			a = this.f.scale(1/this.m);
		}
		else
			a = new Vector2D(); //aceleracion = (0,0)
		
		//p = p + v*t + 1/2*a*t^2
		this.p = this.p.plus(this.v.scale(dt).plus(a.scale(dt*dt*1/2)));
		//v = v + a*t
		this.v = this.v.plus(a.scale(dt));
	
	}

}
