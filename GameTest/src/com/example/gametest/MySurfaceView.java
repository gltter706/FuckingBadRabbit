package com.example.gametest;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

public class MySurfaceView extends SurfaceView implements Callback
{
	private SurfaceHolder sfh;
	private Paint paint;
	private float wallW,wallH;
	private boolean flag;
	private Player player;
	private ArrayList<RectF> wallList=new ArrayList<>();
	public Context context;
	private RectF desRectF;
	private int map[][] =new int[][]{
			{0,1,0,1,0,1,0,1,0,0},
			{0,0,0,1,0,0,0,0,0,1},
			{1,0,0,0,1,0,0,0,0,1},
			{0,0,1,0,0,0,0,0,1,0},
			{0,0,0,0,1,0,0,0,1,0},
			{0,0,0,0,0,0,1,0,0,1},//5,6和5,7变
			{0,1,0,0,1,1,1,0,1,0},
			{0,0,1,0,0,0,1,0,0,1},
			{1,0,0,0,0,1,1,0,0,0},
			{0,0,0,1,0,1,0,1,0,0}
		};
	public  Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			if(msg.what==0x111)
			{
				Toast toast=Toast.makeText(context, "Bump", Toast.LENGTH_SHORT);
				toast.show();
				player=null;
				AlertDialog.Builder builder=new AlertDialog.Builder(context);
				builder.setTitle("You Lose,Try Again?");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
				
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						// TODO Auto-generated method stub
						player=new Player(10, 10, getWidth(), getHeight(),context);
					}
				});
				AlertDialog dlg=builder.create();
				dlg.setCanceledOnTouchOutside(false);
				dlg.show();
				//Intent intent=new Intent(context, LoseActivity.class);
				//context.startActivity(intent);
			}
			else if(msg.what==0x222)
			{
				Toast toast=Toast.makeText(context, "Win", Toast.LENGTH_SHORT);
				toast.show();
				player=null;
				AlertDialog.Builder builder=new AlertDialog.Builder(context);
				builder.setTitle("You Win,Try again?");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
				{
				
					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						// TODO Auto-generated method stub
						player=new Player(10, 10, getWidth(), getHeight(),context);
					}
				});
				AlertDialog dlg=builder.create();
				dlg.setCanceledOnTouchOutside(false);
				dlg.show();
			}
		}
		
	};

	
	
	public MySurfaceView(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		sfh=getHolder();
		sfh.addCallback(this);
		paint=new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(50.0f);
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
		wallW=getWidth()/10;
		wallH=getHeight()/10;
		desRectF=new RectF(getWidth()-20, getHeight()*9/10, getWidth(), getHeight());
		player=new Player(10, 10, getWidth(), getHeight(),context);
		flag=true;
		new MyThread().start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		// TODO Auto-generated method stub
		flag=false;
	}
	
	public void myDraw()
	{
		Canvas canvas=sfh.lockCanvas();
		canvas.drawColor(Color.WHITE);
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				if(map[i][j]==1)
				{
					canvas.drawRect(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH, paint);
					wallList.add(new RectF(j*wallW, i*wallH, j*wallW+wallW, i*wallH+wallH));
				}
			}
		}
		paint.setColor(Color.RED);
		canvas.drawRect(desRectF, paint);
		paint.setColor(Color.BLACK);
		if(player!=null)
			player.Draw(canvas);
		sfh.unlockCanvasAndPost(canvas);
	}
	public void logic()
	{
		if(player!=null)
		{
			player.logic();
			RectF rect1=new RectF(player.posX, player.posY, player.posX+player.width, player.posY+player.height);
		
			for(int i=0;i<wallList.size();i++)
			{
				RectF rect2=wallList.get(i);
				if(player.bumpWithRect(rect1, rect2))
				{
					handler.sendEmptyMessage(0x111);
				}
			}
			if(player.bumpWithRect(rect1, desRectF))
			{
				handler.sendEmptyMessage(0x222);
			}
		}
	}
	
	
	class MyThread extends Thread
	{
		private int count=0;
		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			while(flag)
			{
				long start=System.currentTimeMillis();
				wallList.clear();
				myDraw();
				logic();
				long end=System.currentTimeMillis();
				if((end-start)<50)
				{
					try
					{
						Thread.sleep(50-(end-start));
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				count++;
				if(count==60)
				{
					count=0;
					int temp=map[5][7];
					map[5][7]=map[5][6];
					map[5][6]=temp;
				}
			}
		}
		
	}


	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		float x=event.getX(),y=event.getY();
		if(player!=null)
		{
			if(x<getWidth()/2)
				player.right=false;
			else {
				player.right=true;
			}
		
			if(y<getHeight()/2)
				player.up=true;
			else {
				player.up=false;
			}
		}
		return super.onTouchEvent(event);
	}
}
