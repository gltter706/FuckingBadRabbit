package shitGame.shitgame01.planeboss;

import android.graphics.Bitmap;

public class Protagonist extends FlyAbs{

	public Protagonist(FlyBehaviour flyBehaviour, float loc_x, float loc_y,
			Bitmap figure) {
		super(flyBehaviour, loc_x, loc_y, figure);
		this.width = figure.getWidth();
		this.height = figure.getHeight();
		this.speed = 50;
	}
	
	public void fly(double angle,double height){
		flyBehaviour.fly(speed, cord, angle,height);
	}
	
	public void speedUp(int speed){
		this.speed += speed;
	}
}
