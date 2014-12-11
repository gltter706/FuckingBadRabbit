package shitGame.shitgame01.utils;

import android.graphics.Bitmap;

public class Enemy
{
	//about enemy attr
	public Bitmap bitmap;
	public float x,y;
	public int w,h;
	public int speed=3;//set by myself
	public int type;
	public int wallNum;
	//use to cal 
	public float wallW,wallH;
	
	public Enemy(int wallNum,int type,float wallW,float wallH,Bitmap bitmap)
	{
		// TODO Auto-generated constructor stub
		this.wallNum=wallNum;
		this.type=type;
		this.wallW=wallW;
		this.wallH=wallH;
		//cal x and y
		int row=wallNum/10;
		int col=wallNum-row*10;
		if(type==1)//vert to run
		{
			x=wallW*col+wallW/2;
			y=wallH*row;
		}
		else if(type==2)//horz to run
		{
			x=wallW*col;
			y=wallH*row+wallH/2;
		}
		//set w and h
		w=h=(int) (wallW/4);
	}

}
