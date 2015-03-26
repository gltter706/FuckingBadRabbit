package shitGame.shitgame01.planeboss;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Background {
	private Bitmap backgrpBitmap;
	private float loc_x;
	private float loc_y;
	private Matrix matrix;
	private float height;
	private float width;
	private int shake = 0;
	
	public Background(Bitmap backBitmap,float loc_x,float loc_y){
		this.backgrpBitmap = backBitmap;
		this.loc_x = loc_x;
		this.loc_y = loc_y;
		this.height = backBitmap.getHeight();
		this.width = backBitmap.getWidth();
		matrix = new Matrix();
	}

	public void Shake(){
		loc_x += 8;
		loc_y += 4;
		shake = 17;
	}
	public void Reset(){
		if(0 == shake){
			return;
		}
		if(0 == shake%2){
			loc_x += 8;
			loc_y += 4;
			shake--;
		}
		else {
			loc_x -= 8;
			loc_y -= 4;
			shake--;
		}
	}
	public Bitmap getBackgrpBitmap() {
		return backgrpBitmap;
	}

	public void setBackgrpBitmap(Bitmap backgrpBitmap) {
		this.backgrpBitmap = backgrpBitmap;
	}

	public float getLoc_x() {
		return loc_x;
	}

	public void setLoc_x(float loc_x) {
		this.loc_x = loc_x;
	}

	public float getLoc_y() {
		return loc_y;
	}

	public void setLoc_y(float loc_y) {
		this.loc_y = loc_y;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	
}
