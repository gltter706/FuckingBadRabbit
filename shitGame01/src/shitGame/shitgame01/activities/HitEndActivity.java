package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class HitEndActivity extends Activity{
	private Intent intent;
	private Animation animation;
	private ImageView iv_cartoon;
	private int cur_mission;
	private Button btn_hitEnd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_hit_end);
		Intent get_missionIntent = getIntent();
		cur_mission = get_missionIntent.getIntExtra("cur_selected_mission", 0);
		intent = getIntent();
		iv_cartoon = (ImageView) findViewById(R.id.iv_hitendcartoon);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
		iv_cartoon.setAnimation(animation);
		btn_hitEnd = (Button) findViewById(R.id.btn_hitendcartoon);
	}
	public void beginHit(View v){
		Intent intent = new Intent(
				HitEndActivity.this,
				SelectItemActivity.class);
		intent.putExtra("cur_selected_mission",
				cur_mission);
		HitEndActivity.this.startActivity(intent);
		HitEndActivity.this.finish();
		HitEndActivity.this.finish();
	}

}
