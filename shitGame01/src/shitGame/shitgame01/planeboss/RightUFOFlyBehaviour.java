package shitGame.shitgame01.planeboss;

import shitGame.shitgame01.planeboss.Coordinate;
import shitGame.shitgame01.planeboss.FlyBehaviour;

public class RightUFOFlyBehaviour implements FlyBehaviour{

	boolean flag = false;
	double SHAKE = 1;
	@Override
	public void fly(double speed, Coordinate cord, double angle,double height) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fly(double speed, Coordinate cord) {
		cord.x -= speed;
		if(flag){
			cord.y += SHAKE;
		}
		else{
			cord.y -= SHAKE;
		}
	}

}
