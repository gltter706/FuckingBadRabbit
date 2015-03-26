package shitGame.shitgame01.activities;

import shitGame.shitgame01.planeboss.PlaneSurfaceView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class BossPlaneActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new PlaneSurfaceView(this));
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}

}
