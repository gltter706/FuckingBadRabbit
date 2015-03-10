package shitGame.shitgame01.utils;

import android.R.integer;
import android.graphics.Matrix;
import android.util.Log;

public class CalHelper
{
	public static Matrix getScaleMatrix(float toWidth,int fromWidth,float toHeight,int fromHeight)
	{
		Matrix matrix=new Matrix();
		matrix.postScale(toWidth/fromWidth, toHeight/fromHeight);
		return matrix;
	}
	
	public static int getCurCol(float player_x,float wallW)
	{
		return (int) (player_x/wallW);
	}
	public static int getCurRow(float player_y,float wallH)
	{
		return (int) (player_y/wallH);
	}
}
