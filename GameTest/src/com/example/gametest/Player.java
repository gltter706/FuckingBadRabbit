package com.example.gametest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class Player
{
	public float posX,posY;
	public int width=10,height=10;
	private float screenW,screenH;
	private float delta=3;
	public boolean up=false,right=true;
	
	public Player(float posX,float posY,float screenW,float screenH,Context context)
	{
		this.posX=posX;
		this.posY=posY;
		this.screenW=screenW;
		this.screenH=screenH;
	}
	public void Draw(Canvas canvas)
	{
		Paint paint=new Paint();
		paint.setColor(Color.RED);
		canvas.drawRect(posX, posY, posX+width, posY+height, paint);
	}
	public void logic()
	{
		if(up)
			posY-=delta;
		else {
			posY+=delta;
		}
		if(right)
			posX+=delta;
		else {
			posX-=delta;
		}
		
		
		if(posY<0 || posY>=screenH)
			up=!up;
		if(posX<0 || posX>=screenW)
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
