package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.R.string;
import shitGame.shitgame01.constant.AppConstant;
import shitGame.shitgame01.interfaces.MusicController;
import shitGame.shitgame01.services.PlayMusicService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Audio.Media;
import android.telephony.ServiceState;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartActivity extends Activity {
	  private final String TAG="activity.StartActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);

		//设置btn_start
		Button bt_start=(Button)findViewById(R.id.btn_startmenu_play);
//	    AnimationDrawable animationDrawable;
//	    animationDrawable=(AnimationDrawable)bt_start.getBackground();
//	    animationDrawable.start();
		bt_start.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*判断是否第一次进入游戏 */
				SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
				boolean is_first_play=sharedPreferences.getBoolean("is_first_play", true);
				/*设首进为真，播放动画*/				
				if(is_first_play){					
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putBoolean("is_first_play", false);
				editor.commit();
				Intent intent=new Intent(StartActivity.this,FirstPlayDisplayActivity.class);
				startActivity(intent);
				}
				else{
				/*直接进入选关*/
					Intent intent=new Intent(StartActivity.this,SelectMissionActivity.class);
					startActivity(intent);
				}
			}
		});
		Log.d(TAG, "start_btn");
		//设置btn_shop
		Button bt_shop=(Button)findViewById(R.id.btn_startmenu_shop);
		bt_shop.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartActivity.this,ShopActivity.class);
				startActivity(intent);				
			}
		});
		
		//设置imageView
		ImageView roler=(ImageView)findViewById(R.id.imgv_startmenu_role);
		roler.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast=Toast.makeText(getApplicationContext(), "thanks for your support",Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 160, 40);
				toast.show();
			}
		});
		Log.d(TAG, "imageView");
		//设置btn_exit
/*		Button bt_exit=(Button)findViewById(R.id.btn_exit);
		bt_exit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();				
			}
		});
*/
		
		//设置btn_music
				Button bt_music=(Button)findViewById(R.id.btn_music);
				bt_music.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MusicController musicController=new MusicController();
						musicController.onAttach(StartActivity.this);
						if(AppConstant.MusicPlayState.CURRENT_PLAY_STATE==AppConstant.MusicPlayState.PLAY_STATE_PAUSE){
							musicController.playMusic(AppConstant.MusicPlayState.PLAY_STATE_PLAYING,AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
						}
						else {
							musicController.playMusic(AppConstant.MusicPlayState.PLAY_STATE_PAUSE, AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
						}
					}
				});
				Log.d(TAG, "music_btn");
				
		//初始化音乐播放
//				this.startService(new Intent(this,PlayMusicService.class));
                SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
                int is_music_on=sharedPreferences.getInt("is_music_on", AppConstant.MusicPlayState.PLAY_STATE_PLAYING);
        		Log.d(TAG, "share");
                int curScene=AppConstant.MusicPlayState.SCENE_NOT_BATTLING;
                MusicController musicController=new MusicController();
           		Log.d(TAG, "new msctl");
                musicController.onAttach(StartActivity.this);
                musicController.playMusic(is_music_on,curScene);
        		Log.d(TAG, "create_finish");
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}
    
//	 public boolean onKeyDown(int keyCode, KeyEvent event) {
//		  // TODO Auto-generated method stub
//		  if(keyCode == KeyEvent.KEYCODE_BACK){
//			new AlertDialog.Builder(this)
//			.setMessage("确定退出?")
//			.setNegativeButton(R.string.dlg_exit_cancel, new OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface arg0, int arg1) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//		   return true;
//		  }
//		  return super.onKeyDown(keyCode, event);
//		 }
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
        Intent intent = new Intent(StartActivity.this,PlayMusicService.class);
		stopService(intent);
	}
    
}