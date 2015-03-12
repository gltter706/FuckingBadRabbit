package shitGame.shitgame01.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Key
{
	public int pos;
	public boolean get_flag;
	public boolean real_flag;
	public int[] key_wall_list;
	
	public Key(int pos,boolean real_flag,int[] key_wall_list)
	{
		this.pos=pos;
		this.get_flag=false;
		this.real_flag=real_flag;
		this.key_wall_list=key_wall_list;
	}
	
	public void drawKey(Canvas canvas,Paint paint,Bitmap key_bmp,float wallW,float wallH)
	{
		if(!get_flag)
		{
			int row=pos/10;
			int col=pos-row*10;
			RectF key_rectf=new RectF(col*wallW, row*wallH, col*wallW+wallW, row*wallH+wallH);
		
			canvas.save();
			canvas.clipRect(key_rectf);
			canvas.drawBitmap(key_bmp, key_rectf.left, key_rectf.top, paint);//draw dist
			canvas.restore();
		}
	}
	public static Key copy(Key key)
	{
		Key ret_key=new Key(key.pos, key.real_flag, key.key_wall_list);
		ret_key.real_flag=key.real_flag;
		return ret_key;
	}
}
