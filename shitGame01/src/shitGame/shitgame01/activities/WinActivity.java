package shitGame.shitgame01.activities;

import java.util.Calendar;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends Activity
{
	Calendar date;
	boolean unlock = false;
	private MyHandler myHandler;
	private Bag bag;
	private int coinBonus = 0;
	private long timeCost;
	private final int TIME_LIMIT = 15;
	private final int SolidBonus = 3;
	private final double Weight = 0.005;
	/* (non-Javadoc)
	 * @see android.app.Activity#onBackPressed()
	 */
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
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		myHandler = new MyHandler();
		setContentView(R.layout.activity_win_main);
		final Button btn_back = (Button) findViewById(R.id.btn_winback);
		final Button btn_continueButton = (Button) findViewById(R.id.btn_wincontinue);
		
		Intent data = getIntent();
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
			editor.putInt("cur_mission_num", unlock_mission_num+1);  
			editor.commit();  
			unlock = true;
		}
		Log.e("unlock", unlock_mission_num+"");
		Log.e("cur", cur_selected_mission+"");
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_back.setBackgroundColor(Color.YELLOW);
				Intent intent = new Intent(WinActivity.this,SelectMissionActivity.class);
				WinActivity.this.startActivity(intent);
				WinActivity.this.finish();
			}
		});
		btn_continueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn_continueButton.setBackgroundColor(Color.YELLOW);
				Intent intent = new Intent(WinActivity.this,SelectItemActivity.class);
				intent.putExtra("cur_selected_mission", cur_selected_mission+1);
				WinActivity.this.startActivity(intent);
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
		if(timeCost < TIME_LIMIT){
			coinBonus = 0;
		}
		else{
			coinBonus =(int) (Weight*( timeCost)+SolidBonus);
		}
		
		//tbag.sumItem(WinActivity.this);
		//tbag.writeItem(WinActivity.this,coinNum ,tbag.getCoinInt()+coinBonus);
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
