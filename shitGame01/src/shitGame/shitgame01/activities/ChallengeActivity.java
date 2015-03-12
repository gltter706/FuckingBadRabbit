package shitGame.shitgame01.activities;

import shitGame.shitgame01.utils.Bag;
import shitGame.shitgame01.utils.ChallengeSurfaceView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ChallengeActivity extends Activity
{
	private TextView tv;
	private Bag bag;
	private ChallengeSurfaceView challengeSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Intent intent=getIntent();
		bag=(Bag) intent.getSerializableExtra("bag");
		challengeSurfaceView=new ChallengeSurfaceView(ChallengeActivity.this, bag);
		setContentView(challengeSurfaceView);
		
	}
	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		challengeSurfaceView.show_pause_Dlg();
	}

}
