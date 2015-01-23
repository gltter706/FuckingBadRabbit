package shitGame.shitgame01.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Player
{
	public Bitmap bitmap,bitmapforleft;
	public float x,y;
	public int w,h;
	public int speed;
	public float screenW,screenH;
	public boolean right,up;
	public Item item1;
	public Item item2;
	
	public Player(float x,float y,int w,int h,float screenW,float screenH,Bitmap bmp)
	{
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.right=true;
		this.up=false;
		this.speed=2;
		this.screenW=screenW;
		this.screenH=screenH;
		
		Matrix matrix=new Matrix();
		matrix.postScale(((float)w)/bmp.getWidth(), ((float)h)/bmp.getHeight());
		this.bitmap=Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
		Matrix matrix2=new Matrix();
		matrix2.postScale(-1, 1);
		this.bitmapforleft=Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix2, true);
	}
	
	public void drawPlayer(Canvas canvas, Paint paint)
	{
		canvas.save();
		canvas.clipRect(new RectF(x, y, x+w, y+h));
		if(this.right)
			canvas.drawBitmap(bitmap, x, y, paint);
		else {
			canvas.drawBitmap(bitmapforleft, x, y, paint);
		}
		canvas.restore();
	}
	
	public void logic()
	{
		if(up)
			y-=speed;
		else {
			y+=speed;
		}
		if(right)
			x+=speed;
		else {
			x-=speed;
		}
		
		if(y<0 || y>=screenH-h)
			up=!up;
		if(x<0 || x>=screenW-w)
			right=!right;
	}
	
	public boolean bumpWithRect(RectF rect1,RectF rect2)
	{
		float x1=rect1.left,y1=rect1.top,w1=rect1.width(),h1=rect1.height();
		float x2=rect2.left,y2=rect2.top,w2=rect2.width(),h2=rect2.height();
		if(x1>=x2 && x1>=x2+w2)
			return false;
		else if(x1<=x2 && x1+w1<=x2)
			return false;
		else if(y1>=y2 && y1>=y2+h2)
			return false;
		else if(y1<=y2 && y2>=y1+h1)
			return false;
		return true;
	}
}
