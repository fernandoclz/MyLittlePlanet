package simulator.model;

import simulator.misc.Vector2D;

public class MovingBody extends Body{

	
	public MovingBody(String id, String gid, Vector2D p, Vector2D v, double m) throws IllegalArgumentException {
		super(id,gid,p,v,m);
		if(id != null && gid != null && v != null && p != null) {
			if(id.trim().length()>0)
				this.id = id;
			else
				throw new IllegalArgumentException();
			if(gid.trim().length()>0)
				this.gid = gid;
			else
				throw new IllegalArgumentException();//entonces, si esto se lanza, lo que sigue de codigo no se ejecuta y pasa al catch
			this.v = v;
			this.f = new Vector2D();
			this.p = p;
			if(m > 0)
				this.m = m;
			else
				throw new IllegalArgumentException();
		}
		else
			throw new IllegalArgumentException();
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
