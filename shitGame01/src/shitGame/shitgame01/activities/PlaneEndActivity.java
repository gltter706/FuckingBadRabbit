package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PlaneEndActivity extends Activity {
	private Intent intent;
	private Animation animation;
	private ImageView iv_cartoon;
	private int cur_mission;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plane_end);
		Intent get_missionIntent = getIntent();
		cur_mission = get_missionIntent.getIntExtra("cur_selected_mission", 0);
		intent = getIntent();
		iv_cartoon = (ImageView) findViewById(R.id.iv_planeendcartoon);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
		iv_cartoon.setAnimation(animation);
	}
	public void beginHit(View v){
		Intent intent = new Intent(
				PlaneEndActivity.this,
				SelectItemActivity.class);
		intent.putExtra("cur_selected_mission",
				cur_mission);
		PlaneEndActivity.this.startActivity(intent);
		PlaneEndActivity.this.finish();
		PlaneEndActivity.this.finish();
	}

}
