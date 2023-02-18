package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	private double G;
	
	public NewtonUniversalGravitation (double G) {
		if(G < 0)
			throw new IllegalArgumentException(); //?
		
		else {
			this.G = G;
		}
	}
	
	@Override
	public void apply(List<Body> bs) { //cambia
		// TODO Auto-generated method stub
		for(Body b: bs) {
			if(b.m == 0) { //si m = 0, ponemos los vectores de aceleración y velocidad de
							//Bi a (0, 0) => entonces Body debe tener un atributo aceleración?
				//b.a = new Vector2D();
				b.v = new Vector2D(); 
			}
			else {
				
			}
		}
	}
	
}
