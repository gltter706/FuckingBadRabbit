package shitGame.shitgame01.planeboss;

import shitGame.shitgame01.planeboss.Coordinate;
import shitGame.shitgame01.planeboss.FlyBehaviour;

public class ProFlyBehaviour implements FlyBehaviour {

	private double SHAKE = 0.5;
	private boolean down  = false;

	@Override
	public void fly(double speed, Coordinate cord, double angle,double height) {
		final double G = 0.8;
		double v0 = speed*Math.sin(angle);
		double vt = v0 - Math.sqrt( 2*G*(height - cord.y));

		cord.y = (float) (cord.y - vt);
		cord.x = (float) (cord.x + speed*Math.cos(angle));
		int ran = (int) (Math.random() * 10);
		if (ran > 5) {
			cord.x -= SHAKE;
		} else {
			cord.x += SHAKE;
		}
	}

	@Override
	public void fly(double speed, Coordinate cord) {
		throw new UnsupportedOperationException();
		
	}

	
}
