package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class BossPlaneCartoon extends Activity{

	private Intent intent;
	private Intent newIntent;
	private Bag bag;
	private Animation animation;
	private ImageView iv_cartoon;
	private long spend_time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_bossplanecartoon);
		intent = getIntent();
		spend_time = intent.getLongExtra("spend_time", 0xfffff);
		bag = (Bag) intent.getSerializableExtra("bag");
		iv_cartoon = (ImageView) findViewById(R.id.iv_planecartoon);
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
	}
	
	public void beginBoss(View v){
		newIntent = new Intent(BossPlaneCartoon.this,BossPlaneActivity.class);
		newIntent.putExtra("bag", bag);
		newIntent.putExtra("spend_time", spend_time);
		startActivity(newIntent);
		BossPlaneCartoon.this.finish();
	}
}
