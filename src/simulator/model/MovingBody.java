package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{

	
	public MovingBody(String id, String gid, Vector2D p, Vector2D v, double m){
		super(id,gid,p,v,m);
	}

	@Override
	void advance(double dt) {
		// TODO Auto-generated method stub
		Vector2D a = new Vector2D(); //aceleracion //creo que iria como atributo
		//calculo aceleracion
		if(m != 0) {
			//a = f/m
			a = this.f.scale(1/this.m);
		}
		else {
			a = new Vector2D(); //aceleracion = (0,0)
		}
		
		//p = p + v*t + 1/2*a*t^2
		this.p = this.p.plus(this.v.scale(dt).plus(a.scale(0.5*dt*dt)));
		//v = v + a*t
		this.v = this.v.plus(a.scale(dt));
	
	}

}
