package shitGame.shitgame01.utils;

import android.graphics.Bitmap;

public class Player
{
	public Bitmap bitmap;
	public float x,y;
	public int w,h;
	public int speed;
	public Item item1;
	public Item item2;
	
	public Player(float x,float y,int w,int h,Bitmap bmp)
	{
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.bitmap=bmp;
	}
}
