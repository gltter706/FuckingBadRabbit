package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class BossPlaneCartoon extends Activity {

	private Intent intent;
	private Intent newIntent;
	private boolean start;
	private Bag bag;
	private Button btn_plane_cartoon;
	private GestureDetector gestureDetector;
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
		btn_plane_cartoon = (Button) findViewById(R.id.btn_planecartoon);
		start = false;
		animation = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out);
		iv_cartoon.setAnimation(animation);
		gestureDetector = new GestureDetector(BossPlaneCartoon.this,
				new GestureDetector.OnGestureListener() {

					@Override
					public boolean onDown(MotionEvent e) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void onShowPress(MotionEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean onScroll(MotionEvent e1, MotionEvent e2,
							float distanceX, float distanceY) {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void onLongPress(MotionEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						// TODO Auto-generated method stub
						if (!start) {
							if (e2.getX() - e1.getX() < 0) {
								((BitmapDrawable)(iv_cartoon.getDrawable())).getBitmap().recycle();
								iv_cartoon.setImageResource(R.drawable.bg_plane_cartoon2);
								start = true;
								btn_plane_cartoon.setVisibility(View.VISIBLE);
							} else if (e2.getX() - e1.getX() > 0 ) {
								// Log.d(TAG, "flingleft");
								// counter--;
							}
						}
						return false;
					}
				});
	}

	public void beginBoss(View v) {
		newIntent = new Intent(BossPlaneCartoon.this, BossPlaneActivity.class);
		newIntent.putExtra("bag", bag);
		newIntent.putExtra("spend_time", spend_time);
		startActivity(newIntent);
		BossPlaneCartoon.this.finish();
	}
}
