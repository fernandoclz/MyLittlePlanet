package simulator.model;

import simulator.misc.Vector2D;

public class StationaryBody extends Body{

	public StationaryBody(String id, String gid, Vector2D v, Vector2D p, double m) throws IllegalArgumentException {
		super(id, gid, v, p, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	void advance(double dt) { //no hace nada
		// TODO Auto-generated method stub
		
	}

}
