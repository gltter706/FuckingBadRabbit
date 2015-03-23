package shitGame.shitgame01.activities;



import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SelectMissionActivity extends Activity{
	private int missionPics[]=new int[]{1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9,1,2,3,4,5,6,7,8,9};//30 pictrues for 30 ImageButtons
	private int themePics[]=new int[]{1,2,3};//3 pictures for background
	private ImageButton btns[]=new ImageButton[10];
	private LinearLayout bgLayout;
	private GestureDetector gestureDetector;
	
	private int unlock_mission_num=0;
	private int cur_theme_first_mission_num=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_selectmissionactivity_main);
		//初始化部分
		for(int i=0;i<10;i++)
		{
			btns[i]=(ImageButton) findViewById(R.id.selectmission_btn0+i);
			
		}
		bgLayout=(LinearLayout) findViewById(R.id.selectmission_bg);
		
		SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
		unlock_mission_num=sharedPreferences.getInt("cur_mission_num", 0);
		cur_theme_first_mission_num=((int)(unlock_mission_num/10))*10;
		
		gestureDetector=new GestureDetector(SelectMissionActivity.this, new GestureDetector.OnGestureListener()
		{
			
			@Override
			public boolean onSingleTapUp(MotionEvent e)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onShowPress(MotionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
					float distanceY)
			{
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onLongPress(MotionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
					float velocityY)
			{
				// TODO Auto-generated method stub
				if(e2.getX()-e1.getX()<0)
				{
					//左邊滑動
					System.out.println("LEFT   ---------->");
					//加载后面关卡
					if(cur_theme_first_mission_num+10<=unlock_mission_num)
					{
						if(unlock_mission_num-(cur_theme_first_mission_num+10)<10)
						{
							load_Btn(unlock_mission_num);
							cur_theme_first_mission_num+=10;
						}else {
							cur_theme_first_mission_num+=10;
							load_Btn(cur_theme_first_mission_num+9);
						}
					}
					
				}
				else if(e2.getX()-e1.getX()>0)
				{
					//右邊滑動
					System.out.println("RIGHT  <----------");
					//加载前面的关卡
					if(cur_theme_first_mission_num!=0)
					{
						cur_theme_first_mission_num-=10;
						load_Btn(cur_theme_first_mission_num+9);
					}
				}
				return false;
			}
			
			@Override
			public boolean onDown(MotionEvent e)
			{
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		//下面利用unlock_mission_num的值来判断加载哪十关
		load_Btn(unlock_mission_num);
	}


	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
		
	}

	public void load_Btn(int unlock_mission_num)
	{
		if(unlock_mission_num>=0 && unlock_mission_num<10)
		{
			bgLayout.setBackgroundResource(R.drawable.bg_selectmissionactivity_1);
			for(int i=0;i<10;i++)
			{
				if(i>unlock_mission_num)
				{
					btns[i].setImageResource(R.drawable.btn_selectmissionactivity_pic_lock);
					btns[i].setEnabled(false);
				}
				else {
					btns[i].setImageResource(R.drawable.btn_selectmission_pic_selector1_1+i);
					btns[i].setEnabled(true);
				}
				
			}
		}
		else if(unlock_mission_num>=10 && unlock_mission_num<20)
		{
			bgLayout.setBackgroundResource(R.drawable.bg_selectmissionactivity_2);
			for(int i=0;i<10;i++)
			{
				if(i>unlock_mission_num-10)
				{
					btns[i].setImageResource(R.drawable.btn_selectmissionactivity_pic_lock);
					btns[i].setEnabled(false);
				}
				else {
					btns[i].setImageResource(R.drawable.btn_selectmission_pic_selector2_1+i);
					btns[i].setEnabled(true);
				}
				
			}
		}
		else if(unlock_mission_num>=20 && unlock_mission_num<30)
		{
			bgLayout.setBackgroundResource(R.drawable.bg_selectmissionactivity_3);
			for(int i=0;i<10;i++)
			{
				if(i>unlock_mission_num-20)
				{
					btns[i].setImageResource(R.drawable.btn_selectmissionactivity_pic_lock);
					btns[i].setEnabled(false);
				}
				else {
					btns[i].setImageResource(R.drawable.btn_selectmission_pic_selector3_1+i);
					btns[i].setEnabled(true);
				}
				
			}
		}
	}

	public void selectmission_btn_OnClick(View view)
	{
		int cur_selected_mission=view.getId()-R.id.selectmission_btn0+cur_theme_first_mission_num;
		Intent intent=new Intent(SelectMissionActivity.this, SelectItemActivity.class);
		intent.putExtra("cur_selected_mission", cur_selected_mission);
		startActivity(intent);
		finish();
	}



	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
		unlock_mission_num=sharedPreferences.getInt("cur_mission_num", 0);
		load_Btn(unlock_mission_num);
		super.onResume();
	}
}
