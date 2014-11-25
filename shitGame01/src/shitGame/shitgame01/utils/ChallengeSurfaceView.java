package shitGame.shitgame01.utils;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class ChallengeSurfaceView extends SurfaceView implements Callback
{
	private Context context;
	
	public ChallengeSurfaceView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
