package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.constant.AppConstant;
import shitGame.shitgame01.interfaces.MusicController;
import shitGame.shitgame01.services.PlayMusicService;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity {
	//private long back_pressed;

	private final String TAG = "activity.StartActivity";
	private PopupWindow mPop;


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
		Log.d(TAG, "imageView");
		//设置popWindow
		ImageButton btn_popWindowButton=(ImageButton) findViewById(R.id.btn_popWindow);
		btn_popWindowButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

		          if (mPop == null) {
					View view = getLayoutInflater().inflate(
							R.layout.dlg_startact_dropdown, null);
					mPop = new PopupWindow(view, LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);

					// 设置btn_vote
					Log.d(TAG, "btn_vote_finish");
					ImageButton btn_vote = (ImageButton) view
							.findViewById(R.id.btn_vote);
					btn_vote.setOnClickListener(new VoteListener());
	

					// 设置btn_music
					ImageButton btn_music = (ImageButton) view
							.findViewById(R.id.btn_music);
					Log.d(TAG, "btn_music");
					btn_music.setOnClickListener(new MusicButtonListener());

					// 设置btn_feedback
					ImageButton btn_feedback = (ImageButton) view
							.findViewById(R.id.btn_feedback);
					btn_feedback.setOnClickListener(new FeedbackListener());
					Log.d(TAG, "btn_feedback");
					
				}
		       if(mPop.isShowing())
					mPop.dismiss();
		       else
		    	   mPop.showAsDropDown(v);
				
			}
		});
		Log.d(TAG, "popWin");


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
		View view = LayoutInflater.from(StartActivity.this).inflate(
				R.layout.dlg_startact_onback, null);
		builder.setView(view);
		final AlertDialog dlg=builder.create();
		final ImageButton btn_exit_ok=(ImageButton) view.findViewById(R.id.btn_exit_ok);
		final ImageButton btn_exit_cancel=(ImageButton) view.findViewById(R.id.btn_exit_cancel);
		btn_exit_ok.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
			}
		});
		btn_exit_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dlg.dismiss();
			}
		});

		dlg.setCancelable(false);
		dlg.show();
		}
	
	//call when popWindow is not shown

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
			Log.d(TAG, "btn_music0");
			MusicController musicController = new MusicController();
			musicController.onAttach(StartActivity.this);
			Log.d(TAG, "btn_music1");
			if (AppConstant.MusicPlayState.CURRENT_PLAY_STATE == AppConstant.MusicPlayState.PLAY_STATE_PAUSE) {
				musicController.playMusic(
						AppConstant.MusicPlayState.PLAY_STATE_PLAYING,
						AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
			} else {
				musicController.playMusic(
						AppConstant.MusicPlayState.PLAY_STATE_PAUSE,
						AppConstant.MusicPlayState.SCENE_NOT_BATTLING);
			}
			Log.d(TAG, "btn_music2");
		}
    }
    
    private class VoteListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder=new AlertDialog.Builder(StartActivity.this);
			View view = LayoutInflater.from(StartActivity.this).inflate(
					R.layout.dlg_startact_vote, null);
			builder.setView(view);
			final AlertDialog dialog=builder.create();
			final ImageButton btn_vote_ok=(ImageButton) view.findViewById(R.id.btn_vote_ok);
			final ImageButton btn_vote_cancel=(ImageButton) view.findViewById(R.id.btn_vote_cancel);
			btn_vote_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String url = "http://mm.10086.cn/mm2011"; // web address
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(url));
					startActivity(intent);
				}
			});
			btn_vote_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			dialog.show();

		}
		
	}
    
    private class FeedbackListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			AlertDialog.Builder builder=new AlertDialog.Builder(StartActivity.this);
			View view = LayoutInflater.from(StartActivity.this).inflate(
					R.layout.dlg_startact_feedback, null);
			builder.setView(view);
			final AlertDialog dialog=builder.create();
			final ImageButton btn_feedback_ok=(ImageButton) view.findViewById(R.id.btn_feedback_ok);
			final ImageButton btn_feedback_cancel=(ImageButton) view.findViewById(R.id.btn_feedback_cancel);
			btn_feedback_ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent email = new Intent(android.content.Intent.ACTION_SEND);
					email.setType("plain/text");
					String[] emailReciver = new String[]{"zhouyongyang122@gmail.com", "421134693@qq.com"};
					String emailSubject = "你有一条短信";
					String emailBody ="Feedback from ShitGame User";

					//设置邮件默认地址
					email.putExtra(android.content.Intent.EXTRA_EMAIL, emailReciver);
					//设置邮件默认标题
					email.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
					//设置要默认发送的内容
					email.putExtra(android.content.Intent.EXTRA_TEXT, emailBody);
					//调用系统的邮件系统
					startActivity(Intent.createChooser(email, "请选择邮件发送软件"));
					
				}
			});
			btn_feedback_cancel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			dialog.show();

		}
		
	}
}

