package shitGame.shitgame01.utils;

import java.util.ArrayList;
import java.util.Vector;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Map
{
	public Bitmap wall_bmp;
	public Bitmap menu_bmp;
	public Bitmap door1,door2;
	public float wallW,wallH;
	public int map_array[][];
	public int change_wall_num[];
	
	public Map(float wallW,float wallH)
	{
		// TODO Auto-generated constructor stub
		this.wallW=wallW;
		this.wallH=wallH;
	}
	
	void logic()
	{
		for(int i=0;i<change_wall_num.length;i++)
		{
			int row=change_wall_num[i]/10;
			int col=change_wall_num[i]-row*10;
			
			if(map_array[row][col]==1)
				map_array[row][col]=0;
			else if(map_array[row][col]==0)
				map_array[row][col]=1;
		}
	}
	void drawmap(Canvas canvas,Paint paint,ArrayList<RectF> rectF_list)
	{
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(map_array[i][j]==1)//draw  wall
				{
					
					canvas.save();
					canvas.clipRect(new RectF(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH));
					canvas.drawBitmap(wall_bmp, j*wallW, i*wallH, paint);
					canvas.restore();
					rectF_list.add(new RectF(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH));
					
					
				}
				if(map_array[i][j]>1)
				{
					if(map_array[i][j]==2)
					{
						canvas.save();
						canvas.clipRect(new RectF(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH));
						canvas.drawBitmap(door1, j*wallW, i*wallH, paint);
						canvas.restore();
					}
					if(map_array[i][j]==3)
					{
						canvas.save();
						canvas.clipRect(new RectF(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH));
						canvas.drawBitmap(door2, j*wallW, i*wallH, paint);
						canvas.restore();
					}
						
				}
//				if(i==0 && j==9)//draw menu
//				{
//					canvas.save();
//					canvas.clipRect(new RectF(9*wallW, 0*wallH, 9*wallW+wallW, 0*wallH+wallH));
//					canvas.drawBitmap(menu_bmp, 9*wallW, 0*wallH, paint);
//					canvas.restore();
//				}
			}
		}
	}
}
