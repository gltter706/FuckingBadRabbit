package shitGame.shitgame01.activities;

import cn.sharesdk.framework.ShareSDK;
import shitGame.shitgame01.R;
import shitGame.shitgame01.services.PlayMusicService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class InitialActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
		ShareSDK.initSDK(getApplicationContext(),"62272cebe87a");
		this.startService(new Intent(this,PlayMusicService.class));
		this.startActivity(new Intent(this,StartActivity.class));
		this.finish();
	}


}
