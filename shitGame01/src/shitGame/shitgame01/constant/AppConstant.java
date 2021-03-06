package shitGame.shitgame01.constant;


import shitGame.shitgame01.R;


public interface AppConstant {
	
	public static class MusicPlayData {
		//tag which list is playing
		public static int[] CURRENT_MISIC_LIST=AppConstant.MusicList.MUSIC_LIST_NOT_BATTLE;
		//tag which song is playing in the list
		public static int CURRENT_MISIC_INDEX=0;

	}
	
	public class MusicPlayState {
		public static final int PLAY_STATE_PAUSE = 0;
		public static final int PLAY_STATE_PLAYING=1;
		public static int CURRENT_PLAY_STATE = PLAY_STATE_PAUSE;

		//tag which scene is used so as to auto choose which list should be used
		public static final int SCENE_BATTLING=0;
		public static final int SCENE_NOT_BATTLING=1;
		public static final int SCENE_WON=2;
		public static final int SCENE_LOSED=3;
		public static int CURRENT_MISIC_SCENE=SCENE_BATTLING;
	}

	public class MusicList {
		/*// 用来确定使用哪个列表
		public static final int MUSIC_LIST_NAME_NOT_BATTLE=0;
		public static final int MUSIC_LIST_NAME_BATTLE = 1;*/
		
		/*列表*/
		//not battling
		public static final int[]MUSIC_LIST_NOT_BATTLE=new int[]{
			R.raw.notbattle1,

		};
		//battling
		
	}
	
	public class MusicPlayController {
		//
		public static final int MUSIC_STATE_CHANGE=0;
		public static final int MUSIC_SCENE_CHANGE=1;
		public static final int MUSIC_CHECK_HEALTH=3;
		public static String MUSIC_CONTROL_STR ="controll_str";
	}

	public class MusicPlayVariate {

		// 
		//for universal intent guider
		public static String CTL_ACTION = "shitGame.shitgame01.action.CTL_ACTION";
		public static String UPDATE_ACTION = "shitGame.shitgame01.action.UPDATE_ACTION";
	}


}
