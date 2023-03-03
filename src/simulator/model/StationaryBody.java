package simulator.model;

import simulator.misc.Vector2D;

public class StationaryBody extends Body{

	public StationaryBody(String id, String gid, Vector2D p, double m) throws IllegalArgumentException {
		super(id, gid, p, new Vector2D(), m);
		if(id != null && gid != null && v != null && p != null) {
			if(id.trim().length()>0)
				this.id = id;
			else
				throw new IllegalArgumentException();
			if(gid.trim().length()>0)
				this.gid = gid;
			else
				throw new IllegalArgumentException();//entonces, si esto se lanza, lo que sigue de codigo no se ejecuta y pasa al catch
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
		//no hace nada
	}

}
