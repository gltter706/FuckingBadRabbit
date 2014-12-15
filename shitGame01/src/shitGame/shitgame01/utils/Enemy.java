package shitGame.shitgame01.utils;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Enemy
{
	//about enemy attr
	public Bitmap bitmap;
	public float x,y;
	public int w,h;
	public int speed=3;//set by myself
	public int type;
	public int wallNum;
	public int dir=1;
	//use to cal 
	public float wallW,wallH;
	public int row,col;
	
	public Enemy(int wallNum,int type,float wallW,float wallH,Bitmap bitmap)
	{
		// TODO Auto-generated constructor stub
		this.bitmap=bitmap;
		this.wallNum=wallNum;
		this.type=type;
		this.wallW=wallW;
		this.wallH=wallH;
		//set w and h
		w=(int) (wallW/4);
		h=(int) (wallW/4);
		//cal x and y
		row=wallNum/10;
		col=wallNum-row*10;
		if(type==1)//vert to run
		{
			x=wallW*col+wallW/2-w/2;
			y=wallH*row;
		}
		else if(type==2)//horz to run
		{
			x=wallW*col;
			y=wallH*row+wallH/2-h/2;
		}
		Log.d("Test", ""+bitmap.getWidth()+" "+bitmap.getHeight());
		
	}

	public void drawEnemy(Canvas canvas,Paint paint,ArrayList<RectF> rectF_list)
	{
		canvas.save();
		canvas.clipRect(new RectF(x, y, x+w, y+h));
		canvas.drawBitmap(bitmap, x, y, paint);
		canvas.restore();
		rectF_list.add(new RectF(x, y, x+w, y+h));
	}
	
	public void logic()
	{
		if(type==1)//vert to run
		{
			y=y+dir*speed;
			if(y>=wallH*(row+1)-h || y<=wallH*row)
			{
				dir=dir*-1;
			}
		}
		else if(type==2)//horz to run
		{
			x=x+dir*speed;
			if(x>=wallW*(col+1)-w || x<=wallW*col)
			{
				dir=dir*-1;
			}
		}
	}
}
