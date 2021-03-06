package shitGame.shitgame01.activities;

import java.util.Calendar;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinActivity extends Activity
{
	Calendar date;
	private final int MISSION = 30;
	boolean unlock = false;
	private MyHandler myHandler;
	private Bag bag;
	private int coinBonus = 0;
	private long timeCost;
	private final int TIME_LIMIT = 45;
	private final int SolidBonus = 1;
	private final double Weight = 1.5;
	private TextView tv_superBonus;
	private int boosBonus = -1;
	private Intent data;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Intent intent = new Intent(WinActivity.this,StartActivity.class);
		WinActivity.this.startActivity(intent);
		WinActivity.this.finish();
	}
	private class MyHandler extends Handler{
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			if(0x110 == msg.what){
				String coinString = msg.getData().getString("coin_bonus");
				TextView tv_coinBonus = (TextView) findViewById(R.id.tv_windisplaybonus);
				String display = tv_coinBonus.getText().toString() + coinString;
				tv_coinBonus.setText(display);
			}
			if(0x111 == msg.what){
				String hourString = msg.getData().getString("hour");
				String minuteString = msg.getData().getString("minute");
				String secondString = msg.getData().getString("second");
				TextView tv_costtime = (TextView) findViewById(R.id.tv_wincosttime);
				String display = tv_costtime.getText().toString()
						+ minuteString + " 分 "+secondString +" 秒 ";
				tv_costtime.setText(display);
			}
			if(0x112 == msg.what){
				tv_superBonus = (TextView)findViewById(R.id.tv_boosBonus);
				tv_superBonus.setText(shitGame.shitgame01.R.string.bossBonus);
				tv_superBonus.setText(tv_superBonus.getText().toString() +": "+boosBonus+"!!!");
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myHandler = new MyHandler();
		setContentView(R.layout.activity_win_main);
		final ImageView iv_win = (ImageView) findViewById(R.id.iv_win);
		final Button btn_back = (Button) findViewById(R.id.btn_winback);
		final Button btn_continueButton = (Button) findViewById(R.id.btn_wincontinue);
		final Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_win);
		iv_win.startAnimation(animation);
		
		data = getIntent();
		boosBonus = data.getIntExtra("superBonus", -1);
		if(boosBonus != -1){
			myHandler.sendEmptyMessage(0x112);
		}
		bag = (Bag)data.getSerializableExtra("bag");
		timeCost = data.getLongExtra("spend_time", 0xffffff);
		date = Calendar.getInstance();
		date.setTimeInMillis(timeCost);
		timeCost = date.get(Calendar.SECOND);

		final int cur_selected_mission = bag.getMission();
		final int unlock_mission_num ;
		SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
//		SharedPreferences.Editor editor;
//		editor=sharedPreferences.edit(); 
//		editor.putInt("cur_mission_num", 0);  
//		editor.commit();  
		unlock_mission_num = sharedPreferences.getInt("cur_mission_num", 0);
		
		if(unlock_mission_num == cur_selected_mission){
			SharedPreferences.Editor editor;
			editor=sharedPreferences.edit(); 
			int next_mission = unlock_mission_num + 1 >= MISSION ? unlock_mission_num : unlock_mission_num + 1;
			editor.putInt("cur_mission_num", next_mission);  
			editor.commit();  
			unlock = true;
		}
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WinActivity.this,SelectMissionActivity.class);
				WinActivity.this.startActivity(intent);
				WinActivity.this.finish();
			}
		});
		btn_continueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences sharedPreferences = getSharedPreferences("data",
						MODE_PRIVATE);
				boolean plane_control = sharedPreferences.getBoolean("plane_control", false);
				boolean hit_control = sharedPreferences.getBoolean("hit_control", false);
				if(9 == cur_selected_mission){
					Intent newIntent = new Intent(WinActivity.this, PlaneEndActivity.class);
					newIntent.putExtra("cur_selected_mission", cur_selected_mission+1);
					startActivity(newIntent);
				}
				else if(cur_selected_mission == MISSION - 1){
					Intent intent = new Intent(WinActivity.this,FinishActivity.class);
					startActivity(intent);
					WinActivity.this.finish();
				}
				else if(19 == cur_selected_mission){	
					Intent newIntent = new Intent(WinActivity.this,
							HitEndActivity.class);
					newIntent.putExtra("cur_selected_mission", cur_selected_mission+1);
					startActivity(newIntent);
				}
				else{
					Intent intent = new Intent(WinActivity.this,SelectItemActivity.class);
					intent.putExtra("cur_selected_mission", cur_selected_mission+1);
					WinActivity.this.startActivity(intent);
				}
				WinActivity.this.finish();
			}
		});
		BonusThread bonusthread = new BonusThread();
		bonusthread.run();
	}
	private class BonusThread extends Thread{
		public void run(){
			GenBonus(bag);
			GenTime(date);
		}
	}
	private void GenBonus(Bag tbag){
		String coinNum = getResources().getString(R.string.coin);
		int timeextra = (int)(0.9*(Weight/20*bag.getMission()*TIME_LIMIT-(int)timeCost));
		timeextra = timeextra > 50 ? 50:timeextra;
		coinBonus = (timeextra > 0) ?(int) (Weight*( bag.getMission())+SolidBonus)+timeextra : (int) (Weight*( bag.getMission())+SolidBonus);	
		tbag.sumItem(WinActivity.this);
		if(boosBonus != -1)
			coinBonus += boosBonus;
		tbag.writeItem(WinActivity.this,coinNum ,tbag.getCoinInt()+coinBonus);
		Bundle bundle = new Bundle();
		Message msg = new Message();
		bundle.putString("coin_bonus", ""+coinBonus);
		msg.setData(bundle);
		msg.what = 0x110;
		myHandler.sendMessage(msg);
	}
	private void GenTime(Calendar cal){
		String hourString;
		String minuteString;
		String secondString;
		
		hourString = cal.get(Calendar.HOUR)+"";
		minuteString = cal.get(Calendar.MINUTE)+"";
		secondString = cal.get(Calendar.SECOND)+"";
		
		Bundle bundle = new Bundle();
		Message msg = new Message();
		bundle.putString("hour", ""+hourString);
		bundle.putString("minute", minuteString);
		bundle.putString("second", secondString);
		msg.setData(bundle);
		msg.what = 0x111;
		myHandler.sendMessage(msg);
	}
}
