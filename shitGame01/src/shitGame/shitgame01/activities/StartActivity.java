package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.R.string;
import shitGame.shitgame01.services.PlayMusicService;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.i("hi world","oncreate");
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
				bt_music.setOnClickListener(new OnClickListener(){
					
					@Override
					public void onClick(View v) {
						SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
						/*获取并置反音乐状态*/
						Boolean music_switch=!(sharedPreferences.getBoolean("is_music_on", false));	
						Editor editor=sharedPreferences.edit();
						editor.putBoolean("is_music_on", music_switch);
						editor.commit();
		                Intent intent = new Intent(StartActivity.this,PlayMusicService.class);
		                /*开始音乐 */
		                if(music_switch){
		                	intent.putExtra("msc_playing", true);
		                	startService(intent);
		                }
		                /*暂停音乐*/
		                else{
		                	stopService(intent);
		                }
		                
		            }
				});
				
		//初始化音乐播放
                SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
				boolean is_music_on=sharedPreferences.getBoolean("is_music_on", true);
                Intent intent = new Intent(StartActivity.this,shitGame.shitgame01.services.PlayMusicService.class);
             
				intent.putExtra("msc_playing", is_music_on);
                startService(intent);
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