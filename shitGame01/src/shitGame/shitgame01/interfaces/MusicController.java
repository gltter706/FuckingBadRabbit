package shitGame.shitgame01.interfaces;

import shitGame.shitgame01.constant.AppConstant;
import android.app.Activity;
import android.content.Intent;


public class MusicController {
	Activity activity;
	
    public MusicController(){

    }
    //使用前先调用这个方法传入Activity
    public void onAttach(Activity mActivity){
    	activity=mActivity;  
    
    }
	//根据参数判断需要广播的音乐控制指令
	 public void playMusic(int playState,int playScene) {
		 if(playScene!=AppConstant.MusicPlayState.CURRENT_MISIC_SCENE){
			AppConstant.MusicPlayState.CURRENT_MISIC_SCENE=playScene;
			AppConstant.MusicPlayState.CURRENT_PLAY_STATE=playState;
			SendBroadcast(AppConstant.MusicPlayController.MUSIC_SCENE_CHANGE);
			return;
		 }
		 else if(playState!=AppConstant.MusicPlayState.CURRENT_PLAY_STATE){
			AppConstant.MusicPlayState.CURRENT_PLAY_STATE=playState;
			SendBroadcast(AppConstant.MusicPlayController.MUSIC_STATE_CHANGE);
			return;
		 }
		 else SendBroadcast(AppConstant.MusicPlayController.MUSIC_CHECK_HEALTH);
		 
	}
	 
	 //打包控制指令发送给PlayMusicService
	private void SendBroadcast(int controlType) {

		Intent sendIntent = new Intent(AppConstant.MusicPlayVariate.CTL_ACTION);
		sendIntent.putExtra(AppConstant.MusicPlayController.MUSIC_CONTROL_STR,
				controlType);
		activity.sendBroadcast(sendIntent);
	}

}
