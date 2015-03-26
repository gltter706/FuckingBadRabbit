package shitGame.shitgame01.planeboss;

import android.graphics.Bitmap;

public class UFO extends FlyAbs{
	private int VALUE = 5;
	private int value;
	private boolean isBoom;
	private int crack = 0;
	
	public UFO(FlyBehaviour flyBehaviour, float loc_x, float loc_y,Bitmap figure) {
		super(flyBehaviour, loc_x, loc_y,figure); 
		this.width = figure.getWidth();
		this.height = figure.getHeight();
		speed = 35;
		value = VALUE;
		isBoom = false;
	}
	
	public void fly(){
		flyBehaviour.fly(speed, cord);
	}
	
	public void speedUp(double up){
		speed += up;
	}
	
	public int getBonus(){
		return value;
	}
	
	public void Boom(Bitmap boom){
		setFigure(boom);
		isBoom = true;
	}
	
	public boolean Crack(Bitmap boom){
		crack ++;
		if(4 == crack){
			destroyed();
			return true;
		}
		else if(2 == crack){
			setFigure(boom);
		}
		return false;
	}
	
	public boolean getIsBoom(){
		return isBoom;
	}
}
