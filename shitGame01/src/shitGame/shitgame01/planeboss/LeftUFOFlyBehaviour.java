package shitGame.shitgame01.planeboss;


public class LeftUFOFlyBehaviour implements FlyBehaviour{

	boolean flag = false;
	float SHAKE = 1;
	@Override
	public void fly(double speed, Coordinate cord) {
		cord.x += speed;
		cord.y += SHAKE;
	}
	
	@Override
	public void fly(double speed, Coordinate cord, double angle,double height) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

}
