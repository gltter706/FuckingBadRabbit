package shitGame.shitgame01.utils;

import java.util.ArrayList;
import java.util.Vector;

import com.example.map2.EnemyInfo;
import com.example.map2.Global;

import shitGame.shitgame01.R;
import shitGame.shitgame01.activities.LoseActivity;
import shitGame.shitgame01.activities.StartActivity;
import shitGame.shitgame01.activities.WinActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChallengeSurfaceView extends SurfaceView implements Callback
{
	//Activity gives the context and bag
	private Context context;
	private Bag bag;
	//selected items
	private Item item1,item1_copy;
	private Item item2,item2_copy;
	private boolean item1_ON;
	private int item1_counter;
	private boolean item2_ON;
	private int item2_counter;
	//init the map,player and enemies
	private Map map;
	private Player player;
	private ArrayList<Enemy> enemies;
	//use to test the bump
	private ArrayList<RectF> rectFlist;//only wall,not include dist
	private RectF dist_rectF;
	private RectF player_rectF;
	//surfaceView use
	private SurfaceHolder sfd;
	private float wallH,wallW,screenW,screenH;
	private int cur_selected_mission_num;
	//bitmap
	private Bitmap enemy_bmp;
	private Bitmap dist_bmp;
	private Matrix matrix;
	//thread
	private MyThread myThread;
	private boolean flag=true;
	private int counter=0;
	
	
	
	public ChallengeSurfaceView(Context context,Bag bag)
	{
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.bag=bag;
		cur_selected_mission_num=bag.getMission();
		item1_copy=item1=bag.getSelected_1();
		item2_copy=item2=bag.getSelected_2();
		item1_ON=false;
		item1_counter=0;
		item2_ON=false;
		item2_counter=0;
		
		sfd=getHolder();
		sfd.addCallback(this);
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
		screenW=getWidth();
		screenH=getHeight();
		wallH=getHeight()/10;
		wallW=getWidth()/10;
		rectFlist=new ArrayList<RectF>();
		init();
		myThread=new MyThread();
		myThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0)
	{
		// TODO Auto-generated method stub
		flag=false;
	}

	private void init()
	{
		Global global=new Global();
		matrix=new Matrix();
		
		//init map
		map=new Map(wallW,wallH);
		map.wall_bmp=BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		matrix.postScale(wallW/map.wall_bmp.getWidth(), wallH/map.wall_bmp.getHeight());
		map.wall_bmp=Bitmap.createBitmap(map.wall_bmp,0,0,map.wall_bmp.getWidth(),map.wall_bmp.getHeight(),matrix,true);
		map.menu_bmp=BitmapFactory.decodeResource(getResources(), R.drawable.pause_menu);
		map.menu_bmp=Bitmap.createBitmap(map.menu_bmp,0,0,map.menu_bmp.getWidth(),map.menu_bmp.getHeight(),matrix,true);
		map.map_array=global.ALL_MAP.elementAt(cur_selected_mission_num).map_array;
		map.change_wall_num=global.ALL_MAP.elementAt(cur_selected_mission_num).change_wall_num;
		int dist_rectF_row=global.ALL_MAP.elementAt(cur_selected_mission_num).end_wall_num/10;
		int dist_rectF_col=global.ALL_MAP.elementAt(cur_selected_mission_num).end_wall_num-10*dist_rectF_row;
		dist_rectF=new RectF(dist_rectF_col*wallW, dist_rectF_row*wallH, (dist_rectF_col+1)*wallW, (dist_rectF_row+1)*wallH);
		dist_bmp=BitmapFactory.decodeResource(getResources(), R.drawable.dist);
		dist_bmp=Bitmap.createBitmap(dist_bmp,0,0,dist_bmp.getWidth(),dist_bmp.getHeight(),matrix,true);
		
		//init enemies
		enemies=new ArrayList<Enemy>();
		enemy_bmp=BitmapFactory.decodeResource(getResources(), R.drawable.shit);
		Log.d("Test11111", ""+enemy_bmp.getWidth()+" "+enemy_bmp.getHeight());
		matrix=new Matrix();
		matrix.postScale(wallW/(4*enemy_bmp.getWidth()), wallW/(4*enemy_bmp.getHeight()));
		enemy_bmp=Bitmap.createBitmap(enemy_bmp, 0, 0, enemy_bmp.getWidth(), enemy_bmp.getHeight(), matrix, true);
		Log.d("Test22222", ""+enemy_bmp.getWidth()+" "+enemy_bmp.getHeight());
		Vector<EnemyInfo> enemyinfo_vector=new Vector<EnemyInfo>();
		enemyinfo_vector=global.ALL_MAP.elementAt(cur_selected_mission_num).enemyvector;
		for(int i=0;i<enemyinfo_vector.size();i++)
		{
			EnemyInfo info=enemyinfo_vector.elementAt(i);
			Enemy enemy=new Enemy(info.wall,info.type,wallW,wallH,enemy_bmp);
			enemies.add(enemy);
		}
		
		//init player
		int start_rectF_row=global.ALL_MAP.elementAt(cur_selected_mission_num).start_wall_num/10;
		int start_rectF_col=global.ALL_MAP.elementAt(cur_selected_mission_num).start_wall_num-10*start_rectF_row;
		float start_x=start_rectF_col*wallW+wallW/2-10;
		float start_y=start_rectF_row*wallH+wallH/2-10;
		if(bag.getSelected_role()==null)
		{
			Bitmap role_bmp=BitmapFactory.decodeResource(getResources(), R.drawable.square);
			player=new Player(start_x,start_y,15,15,screenW,screenH,role_bmp,
					BitmapFactory.decodeResource(getResources(), R.drawable.square_in_speedup),
					BitmapFactory.decodeResource(getResources(), R.drawable.square_in_speeddown),
					BitmapFactory.decodeResource(getResources(), R.drawable.square_in_shield));//player is 20px*20px
		}else {
			if(bag.getSelected_role().getItem_id().equals(R.string.round))
				player=new Player(start_x, start_y, 20, 20, screenW,screenH,BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()));
			else if(bag.getSelected_role().getItem_id().equals(R.string.star))
				player=new Player(start_x, start_y, 20, 20, screenW,screenH,BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()));
			else if(bag.getSelected_role().getItem_id().equals(R.string.hexagon))
				player=new Player(start_x, start_y, 20, 20, screenW,screenH,BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()),
						BitmapFactory.decodeResource(getResources(), bag.getSelected_role().getDrawableId()));	
			
		}
	}

	private void myDraw()
	{
		Canvas canvas=sfd.lockCanvas();
		Paint paint=new Paint();
		//draw background theme picture
		canvas.drawColor(Color.WHITE);
		
		//draw map(include wall, menu and dist)
		map.drawmap(canvas, paint, rectFlist);//draw wall and menu
		canvas.save();
		canvas.clipRect(dist_rectF);
		canvas.drawBitmap(dist_bmp, dist_rectF.left, dist_rectF.top, paint);//draw dist
		canvas.restore();
		
		//draw enemies
		for(int i=0;i<enemies.size();i++)
		{
			enemies.get(i).drawEnemy(canvas, paint, rectFlist);
		}
		
		//draw player
		if(item1_ON || item2_ON)
		{
			if(item1_ON)
			{
				if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
				{
					player.drawPlayer_In_speedup(canvas, paint);
				}
				if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
				{
					player.drawPlayer_In_speeddown(canvas, paint);
				}
				if(item1_copy.getItem_id().equals(getResources().getString(R.string.shield)))
				{
					player.drawPlayer_In_shield(canvas, paint);
				}
			}
			if(item2_ON)
			{
				if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
				{
					player.drawPlayer_In_speedup(canvas, paint);
				}
				if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
				{
					player.drawPlayer_In_speeddown(canvas, paint);
				}
				if(item2_copy.getItem_id().equals(getResources().getString(R.string.shield)))
				{
					player.drawPlayer_In_shield(canvas, paint);
				}
			}
		}else {
			player.drawPlayer(canvas, paint);
		}
		
		sfd.unlockCanvasAndPost(canvas);
	}
	
	private void myLogic()
	{
		//first test bumping
		player_rectF=new RectF(player.x, player.y, player.x+player.w, player.y+player.h);
		if(player.bumpWithRect(player_rectF, dist_rectF))
		{
			Log.e("Output", "WIN");
			Intent intent=new Intent(context, WinActivity.class);
			context.startActivity(intent);
			flag=false;
		}
		for(int i=0;i<rectFlist.size();i++)
		{
			if(player.bumpWithRect(player_rectF, rectFlist.get(i)))
			{
				if(item1_ON || item2_ON)
				{
					if(item1_ON)
					{
						if(item1_copy.getItem_id().equals(getResources().getString(R.string.shield)))
						{
							player.right=!player.right;
							player.up=!player.up;
						}
					}
					if(item2_ON)
					{
						if(item2_copy.getItem_id().equals(getResources().getString(R.string.shield)))
						{
							player.right=!player.right;
							player.up=!player.up;
						}
					}
				}else {
					Intent intent=new Intent(context, LoseActivity.class);
					context.startActivity(intent);
					flag=false;
				}
				
			}
		}
		
		//map logic to change wall
		if(counter==39)//2s change wall once
			map.logic();
		//enemies logic to change the position of enemies
		for(int i=0;i<enemies.size();i++)
		{
			enemies.get(i).logic();
		}
		//player logic to change the position of player
		if(item1_ON)
		{
			if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
			{
				if(item1_counter==0)
					player.speed+=1;
				item1_counter++;
			}
			if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
			{
				if(item1_counter==0)
					player.speed-=1;
				item1_counter++;
			}
			if(item1_copy.getItem_id().equals(getResources().getString(R.string.shield)))
			{
				item1_counter++;
			}
		}
		if(item1_counter==59)//持续3s之后
		{
			item1_counter=0;
			item1_ON=false;
			if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
				player.speed-=1;
			if(item1_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
				player.speed+=1;
					
		}
		if(item2_ON)
		{
			if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
			{
				if(item2_counter==0)
					player.speed+=1;
				item2_counter++;
			}
			if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
			{
				if(item2_counter==0)
					player.speed-=1;
				item2_counter++;
			}
			if(item2_copy.getItem_id().equals(getResources().getString(R.string.shield)))
			{
				item2_counter++;
			}
		}
		if(item2_counter==59)//持续3s之后
		{
			item2_counter=0;
			item2_ON=false;
			if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_up)))
				player.speed-=1;
			if(item2_copy.getItem_id().equals(getResources().getString(R.string.speed_down)))
				player.speed+=1;
					
		}
		player.logic();
	}
	
	class MyThread extends Thread
	{

		@Override
		public void run()
		{
			// TODO Auto-generated method stub
			while(flag)
			{
				long start_time=System.currentTimeMillis();
				rectFlist.clear();
				myDraw();
				myLogic();
				counter=(counter+1)%40;//20 frames per second
				long end_time=System.currentTimeMillis();
				if(50>(end_time-start_time))
				{
					try
					{
						Thread.sleep(50-(end_time-start_time));
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
	}

	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		float touch_x=event.getX();
		float touch_y=event.getY();
		//if touch pause menu
		if(touch_x>=9*wallW && touch_x<=screenW && touch_y>=0 && touch_y<=wallH)
		{
			Toast.makeText(context, "pause menu", Toast.LENGTH_SHORT).show();
			
			AlertDialog.Builder builder=new AlertDialog.Builder(context);
			builder.setMessage("Pause");
			View view=LayoutInflater.from(context).inflate(R.layout.pause_dlg_main, null);
			final ImageButton pause_btn0=(ImageButton) view.findViewById(R.id.pause_dlg_btn0);
			final ImageButton pause_btn1=(ImageButton) view.findViewById(R.id.pause_dlg_btn1);
			if(item1!=null)
			{
				pause_btn0.setImageResource(item1.getDrawableId());
			}else {
				pause_btn0.setImageResource(R.drawable.nullitem);
				pause_btn0.setEnabled(false);
			}
			if(item2!=null)
			{
				pause_btn1.setImageResource(item2.getDrawableId());
			}else {
				pause_btn1.setImageResource(R.drawable.nullitem);
				pause_btn1.setEnabled(false);
			}
			pause_btn0.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View arg0)
				{
					// TODO Auto-generated method stub
						item1_ON=true;
						item1_counter=0;
						item1=null;
						pause_btn0.setImageResource(R.drawable.nullitem);
						Toast.makeText(context, "道具已投入使用", Toast.LENGTH_SHORT).show();
						pause_btn0.setEnabled(false);
				}
			});
			pause_btn1.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View arg0)
				{
					// TODO Auto-generated method stub
					item2_ON=true;
					item2_counter=0;
					item2=null;
					pause_btn1.setImageResource(R.drawable.nullitem);
					Toast.makeText(context, "道具已投入使用", Toast.LENGTH_SHORT).show();
					pause_btn1.setEnabled(false);
				}
			});
			
			builder.setView(view);
			builder.setNegativeButton("回到游戏", new DialogInterface.OnClickListener()
			{
				
				@Override
				public void onClick(DialogInterface arg0, int arg1)
				{
					// TODO Auto-generated method stub
					flag=true;
					myThread=new MyThread();
					myThread.start();
				}
			});
			AlertDialog dlg=builder.create();
			dlg.setCanceledOnTouchOutside(false);
			dlg.show();
			
			flag=false;
			return super.onTouchEvent(event);
		}
		else{
			if(touch_x<getWidth()/2)
				player.right=false;
			else {
				player.right=true;
			}
		
			if(touch_y<getHeight()/2)
				player.up=true;
			else {
				player.up=false;
			}
			return super.onTouchEvent(event);
		}
		
	}
}
