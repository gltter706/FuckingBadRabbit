package shitGame.shitgame01.services;

import shitGame.shitgame01.constant.AppConstant;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

 
public class PlayMusicService extends Service implements Runnable,MediaPlayer.OnCompletionListener  {
 
    private final String TAG="services.PlayMusicService";
	private MediaPlayer mMediaPlayer=null;
    private MyReceiver serviceReceiver;
    private HomeKeyEventBroadCastReceiver homeKeyReceiver;

 
    @Override
    public void onCreate() {
        super.onCreate();
		if (mMediaPlayer != null) {
			mMediaPlayer.reset();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
        mMediaPlayer = new MediaPlayer();
        //注册Receiver
        serviceReceiver=new MyReceiver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(AppConstant.MusicPlayVariate.CTL_ACTION);
        registerReceiver(serviceReceiver, intentFilter);
       	  Log.d(TAG,"hasRegist");
       	  Log.d(TAG,""+AppConstant.MusicPlayState.CURRENT_MISIC_SCENE);
       	 homeKeyReceiver = new HomeKeyEventBroadCastReceiver();  
         registerReceiver(homeKeyReceiver, new IntentFilter(  
                       Intent. ACTION_CLOSE_SYSTEM_DIALOGS));  

		
		
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        /*写入退出游戏时的音乐状态*/
		SharedPreferences sharedPreferences=getSharedPreferences("data", MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		editor.putInt("is_music_on", AppConstant.MusicPlayState.CURRENT_PLAY_STATE);
		editor.commit();
		Log.d(TAG, "destroy");
		unregisterReceiver(serviceReceiver);
		unregisterReceiver(homeKeyReceiver);
	    AppConstant.MusicPlayState.CURRENT_MISIC_SCENE=AppConstant.MusicPlayState.SCENE_BATTLING;
		if (mMediaPlayer != null) {
			mMediaPlayer.stop();
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
		
    }
 
    private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.d(TAG,"onReceive1");
			switch (intent.getIntExtra(AppConstant.MusicPlayController.MUSIC_CONTROL_STR
					,AppConstant.MusicPlayController.MUSIC_STATE_CHANGE)){
			case AppConstant.MusicPlayController.MUSIC_SCENE_CHANGE:
				Log.d(TAG,"onReceive2");
				changeMusicScene();
				break;
			case AppConstant.MusicPlayController.MUSIC_STATE_CHANGE:
				Log.d(TAG,"onReceive3");
				changeMusicStates();
				break;
			case AppConstant.MusicPlayController.MUSIC_CHECK_HEALTH:
				Log.d(TAG, "OnHealthCheck");
				musicHealthCheck();
				break;
			default:
				break;
			}
		}
    }

    private  class HomeKeyEventBroadCastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(mMediaPlayer.isPlaying())mMediaPlayer.pause();
		}
    	
    	
    }
  
     
	private void changeMusicStates() {
		// before this method,the state has already change in MusicController
		Log.d(TAG, "changeMusicStates");
		// comfirm:change null into whether resource is set,maybe isLooping()
		// can be used 3/9
		switch (AppConstant.MusicPlayState.CURRENT_PLAY_STATE) {
		case AppConstant.MusicPlayState.PLAY_STATE_PLAYING: {
			try {
				mMediaPlayer.start();
				break;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				mMediaPlayer.reset();
				mMediaPlayer = MediaPlayer
						.create(this,
								AppConstant.MusicPlayData.CURRENT_MISIC_LIST[AppConstant.MusicPlayData.CURRENT_MISIC_INDEX]);
				mMediaPlayer.start();
				break;
			}
		}
		case AppConstant.MusicPlayState.PLAY_STATE_PAUSE:
			try {
				mMediaPlayer.pause();
				break;
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				mMediaPlayer.reset();
				mMediaPlayer = MediaPlayer
						.create(this,
								AppConstant.MusicPlayData.CURRENT_MISIC_LIST[AppConstant.MusicPlayData.CURRENT_MISIC_INDEX]);
				mMediaPlayer.pause();
				break;
			}
		default:
			break;
		}
	}

	

	private void changeMusicScene() {
		mMediaPlayer.reset();
		switch (AppConstant.MusicPlayState.CURRENT_MISIC_SCENE) {
		case AppConstant.MusicPlayState.SCENE_NOT_BATTLING:
			AppConstant.MusicPlayData.CURRENT_MISIC_LIST=AppConstant.MusicList.MUSIC_LIST_NOT_BATTLE;			
			break;

		case AppConstant.MusicPlayState.SCENE_BATTLING:
			AppConstant.MusicPlayData.CURRENT_MISIC_LIST=AppConstant.MusicList.MUSIC_LIST_BATTLE;
            break;

		case AppConstant.MusicPlayState.SCENE_WON:
			AppConstant.MusicPlayData.CURRENT_MISIC_LIST=AppConstant.MusicList.MUSIC_LIST_WON;
			break;
		case AppConstant.MusicPlayState.SCENE_LOSED:
			AppConstant.MusicPlayData.CURRENT_MISIC_LIST=AppConstant.MusicList.MUSIC_LIST_LOSED;
	        break;
		default:
			break;
		}
	     AppConstant.MusicPlayData.CURRENT_MISIC_INDEX=0;
		 mMediaPlayer=MediaPlayer.create(this, AppConstant.MusicPlayData.CURRENT_MISIC_LIST[AppConstant.MusicPlayData.CURRENT_MISIC_INDEX]);
		 Log.d(TAG,"changingMusicScene");
		mMediaPlayer.setOnCompletionListener(new MyOnCompletionListener());
		//检查是否静音
		Log.d(TAG,"onCompletionListener");
		if(AppConstant.MusicPlayState.CURRENT_PLAY_STATE==AppConstant.MusicPlayState.PLAY_STATE_PLAYING){
			mMediaPlayer.start();
		}
		
		
	}
	  
	private void musicHealthCheck(){
		if(((!mMediaPlayer.isPlaying())&&AppConstant.MusicPlayState.CURRENT_PLAY_STATE==AppConstant.MusicPlayState.PLAY_STATE_PLAYING)||
				(mMediaPlayer.isPlaying()&&AppConstant.MusicPlayState.CURRENT_PLAY_STATE==AppConstant.MusicPlayState.PLAY_STATE_PAUSE))
			changeMusicStates();
	}
 
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyOnCompletionListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			if(AppConstant.MusicPlayData.CURRENT_MISIC_INDEX!=
				AppConstant.MusicPlayData.CURRENT_MISIC_LIST.length-1)
				AppConstant.MusicPlayData.CURRENT_MISIC_INDEX++;
			else 
				AppConstant.MusicPlayData.CURRENT_MISIC_INDEX=0;
			//confirm:getApplicationContext is right
			//confirm:if need to set Listener here when use MediaPlayer.create
			mMediaPlayer.reset();
            mMediaPlayer=MediaPlayer.create(PlayMusicService.this, AppConstant.MusicPlayData.CURRENT_MISIC_LIST[AppConstant.MusicPlayData.CURRENT_MISIC_INDEX]);
            mMediaPlayer.setOnCompletionListener(new MyOnCompletionListener());
            mMediaPlayer.start();
		}
		
	 }


	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
