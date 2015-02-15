package shitGame.shitgame01.activities;


import java.util.Calendar;
import java.util.Date;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class LoseActivity extends Activity
{

	Calendar date;
	private MyHandler myHandler;
	private Bag bag;
	private int coinBonus = 0;
	private long timeCost;
	private final int TIME_LIMIT = 15;
	private final int SolidBonus = 1;
	private final double Weight = 0.005;
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Intent intent = new Intent(LoseActivity.this,StartActivity.class);
		LoseActivity.this.startActivity(intent);
		LoseActivity.this.finish();
	}

	private class MyHandler extends Handler{
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			if(0x110 == msg.what){
				String coinString = msg.getData().getString("coin_bonus");
				TextView tv_coinBonus = (TextView) findViewById(R.id.tv_displaybonus);
				String display = tv_coinBonus.getText().toString() + coinString;
				tv_coinBonus.setText(display);
			}
			if(0x111 == msg.what){
				String hourString = msg.getData().getString("hour");
				String minuteString = msg.getData().getString("minute");
				String secondString = msg.getData().getString("second");
				TextView tv_costtime = (TextView) findViewById(R.id.tv_costtime);
				String display = tv_costtime.getText().toString()
						+ minuteString + " 分 "+secondString +" 秒 ";
				tv_costtime.setText(display);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myHandler = new MyHandler();
		setContentView(R.layout.activity_lose_main);
		final Button btn_back = (Button) findViewById(R.id.btn_loseback);
		final Button btn_replayButton = (Button) findViewById(R.id.btn_losecontinue);
		
		Intent data = getIntent();
		bag = (Bag)data.getSerializableExtra("bag");
		timeCost = data.getLongExtra("spend_time", 0xffffff);
		date = Calendar.getInstance();
		date.setTimeInMillis(timeCost);
		timeCost = date.get(Calendar.SECOND);

		final int cur_selected_mission = bag.getMission();
		
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_back.setBackgroundColor(Color.YELLOW);
				Intent intent = new Intent(LoseActivity.this,SelectMissionActivity.class);
				LoseActivity.this.startActivity(intent);
				LoseActivity.this.finish();
			}
		});
		btn_replayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_replayButton.setBackgroundColor(Color.YELLOW);
				Intent intent = new Intent(LoseActivity.this,SelectItemActivity.class);
				intent.putExtra("cur_selected_mission", cur_selected_mission);
				LoseActivity.this.startActivity(intent);
				LoseActivity.this.finish();
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
		if(timeCost < TIME_LIMIT){
			coinBonus = 0;
		}
		else{
			coinBonus =(int) (Weight*( timeCost)+SolidBonus+Weight*tbag.getMission());
		}
		
		tbag.sumItem(LoseActivity.this);
		tbag.writeItem(LoseActivity.this,coinNum ,tbag.getCoinInt()+coinBonus);
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
