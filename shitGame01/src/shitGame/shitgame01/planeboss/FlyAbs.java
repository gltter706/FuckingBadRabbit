package shitGame.shitgame01.planeboss;

import android.R.xml;
import android.graphics.Bitmap;

public abstract class FlyAbs {
	protected double speed;
	protected Coordinate cord;
	protected Bitmap figure;
	protected float width;
	protected float height;
	protected FlyBehaviour flyBehaviour;
	
	public FlyAbs(FlyBehaviour flyBehaviour,float loc_x,float loc_y,Bitmap figure){
		this.flyBehaviour = flyBehaviour;
		cord = new Coordinate(loc_x, loc_y);
		this.figure = figure;
	}
	
	public void destroyed(){
		if(figure.isRecycled() == false)
			figure.recycle();
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public float getLoc_x() {
		return cord.getX();
	}

	public void setLoc_x(float loc_x) {
		cord.setX(loc_x);
	}
	
	public float getLoc_y() {
		return cord.getY();
	}
	
	public void setLoc_y(float loc_y) {
		cord.setY(loc_y);
	}
	
	public Bitmap getFigure() {
		return figure;
	}
	
	public void setFigure(Bitmap figure) {
		this.figure = figure;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
}
