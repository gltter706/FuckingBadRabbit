package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.constant.AppConstant;
import shitGame.shitgame01.interfaces.MusicController;
import shitGame.shitgame01.services.PlayMusicService;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartActivity extends Activity {
	//private long back_pressed;

	private final String TAG = "activity.StartActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);

		// 设置btn_start
		Button bt_start = (Button) findViewById(R.id.btn_startmenu_play);
		bt_start.setOnClickListener(new PlayButtonListener());

		// 设置btn_shop
		Button bt_shop = (Button) findViewById(R.id.btn_startmenu_shop);
		bt_shop.setOnClickListener(new ShopButtonListener());

		// 设置imageView
		ImageView roler = (ImageView) findViewById(R.id.imgv_startmenu_role);
		roler.setOnClickListener(new RolerListener() );

		// 设置btn_music
		Button bt_music = (Button) findViewById(R.id.btn_music);
		bt_music.setOnClickListener(new MusicButtonListener());


		// 初始化音乐播放
		SharedPreferences sharedPreferences = getSharedPreferences("data",
				MODE_PRIVATE);
		int is_music_on = sharedPreferences.getInt("is_music_on",
				AppConstant.MusicPlayState.PLAY_STATE_PLAYING);
		int curScene = AppConstant.MusicPlayState.SCENE_NOT_BATTLING;
		
		MusicController musicController = new MusicController();
		musicController.onAttach(StartActivity.this);
		musicController.playMusic(is_music_on, curScene);
		Log.d(TAG, "create_finish");
	}

	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		Intent intent = new Intent(StartActivity.this, PlayMusicService.class);
		stopService(intent);
	}

	@Override
	public void onBackPressed() {

		// TODO Auto-generated method stub
//		if (back_pressed + 2000 > System.currentTimeMillis()) {
//			android.os.Process.killProcess(android.os.Process.myPid());
//			System.exit(1);
//		} else
//			Toast.makeText(getBaseContext(), "Press once again to exit!",
//					Toast.LENGTH_SHORT).show();
//		    
//		back_pressed = System.currentTimeMillis();

		AlertDialog.Builder builder=new AlertDialog.Builder(StartActivity.this);
		builder.setMessage("确定退出吗");
		View view = LayoutInflater.from(StartActivity.this).inflate(
				R.layout.pause_dlg_main, null);
		builder.setView(view);
		builder.setNegativeButton("回到游戏", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				// TODO Auto-generated method stub
			}
		});
		builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener()
		{
			
			@Override
			public void onClick(DialogInterface arg0, int arg1)
			{
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
			}
		});
		AlertDialog dlg=builder.create();
		dlg.setCancelable(false);
		dlg.show();
		}
	
	//below are Listener classes
    private class PlayButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//判断是否第一次进入游戏
			SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
			boolean is_first_play = sharedPreferences.getBoolean("is_first_play", true);
			if (is_first_play) {
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putBoolean("is_first_play", false);
				editor.commit();
				Intent intent = new Intent(StartActivity.this,FirstPlayDisplayActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(StartActivity.this,SelectMissionActivity.class);
				startActivity(intent);
			}
		}}
    
    private class ShopButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(StartActivity.this,ShopActivity.class);
			startActivity(intent);
		}
	}
    
    private class RolerListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast toast = Toast.makeText(getApplicationContext(),
					"thanks for your support", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 160, 40);
			toast.show();
		}
	}
    
    private class MusicButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			MusicController musicController = new MusicController();
			musicController.onAttach(StartActivity.this);
			if (AppConstant.MusicPlayState.CURRENT_PLAY_STATE == AppConstant.MusicPlayState.PLAY_STATE_PAUSE) {
				musicController.playMusic(
						AppConstant.MusicPlayState.PLAY_STATE_PLAYING,
						AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
			} else {
				musicController.playMusic(
						AppConstant.MusicPlayState.PLAY_STATE_PAUSE,
						AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
			}
		}
    }
}