package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class HitCartoonActivity extends Activity {

	private Intent intent;
	private Intent newIntent;
	private Bag bag;
	private Button btn_hitcartoon;
	private Animation animation;
	private ImageView iv_cartoon;
	private long spend_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_bosshitcartoon);
		intent = getIntent();
		spend_time = intent.getLongExtra("spend_time", 0xfffff);
		bag = (Bag) intent.getSerializableExtra("bag");
		iv_cartoon = (ImageView) findViewById(R.id.iv_Hitcartoon);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
		iv_cartoon.setAnimation(animation);
		btn_hitcartoon = (Button) findViewById(R.id.btn_hitcartoon);
	}

	public void beginHit(View v) {
		SharedPreferences sharedPreferences = getSharedPreferences("data",
				MODE_PRIVATE);
		boolean plane_control = sharedPreferences.getBoolean("hit_control",
				false);
		if (plane_control) {
			newIntent = new Intent(HitCartoonActivity.this,
					BossHitActivity.class);
			newIntent.putExtra("bag", bag);
			newIntent.putExtra("spend_time", spend_time);
			startActivity(newIntent);
			HitCartoonActivity.this.finish();
		} else {
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean("hit_control", true);
			editor.commit();
			newIntent = new Intent(HitCartoonActivity.this,
					HitControlActivity.class);
			newIntent.putExtra("bag", bag);
			newIntent.putExtra("spend_time", spend_time);
			startActivity(newIntent);
			HitCartoonActivity.this.finish();
		}
	}

}
