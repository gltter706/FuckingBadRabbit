package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageView;

public class HitControlActivity extends Activity{
	final int frameOfCartoon = 2; // 修改漫画总帧数
	private GestureDetector gestureDetector;
	private int counter = 0;
	private int[] cartoon_id = new int[frameOfCartoon];
	private ImageView iv_cartoon;
	private Bag bag;
	private long spend_time;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent getIntent = getIntent();
		setContentView(R.layout.layout_hit_control);
		spend_time = getIntent.getLongExtra("spend_time", 0xfffff);
		bag = (Bag) getIntent.getSerializableExtra("bag");
		cartoon_id[0] = R.drawable.hit_control1;
		cartoon_id[1] = R.drawable.hit_control2;

		iv_cartoon = (ImageView) findViewById(R.id.iv_hit_control);
		// this.init_pic();
		gestureDetector = new GestureDetector(HitControlActivity.this,
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
						if (e2.getX() - e1.getX() < 0) {
							if (counter < frameOfCartoon - 1) {
								counter++;
								((BitmapDrawable) (iv_cartoon.getDrawable()))
										.getBitmap().recycle();
								iv_cartoon
										.setImageResource(cartoon_id[counter]);
								// layout.setBackground(cartoonDrawables[counter]);
							} else {
								Intent intent=new Intent(HitControlActivity.this, BossHitActivity.class);
								intent.putExtra("bag", bag);
								intent.putExtra("spend_time", spend_time);
								HitControlActivity.this.startActivity(intent);
								HitControlActivity.this.finish();
							}

						} else if (e2.getX() - e1.getX() > 0 && counter > 0) {
							// Log.d(TAG, "flingleft");
							// counter--;
						}

						return false;
					}
				});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);
	}
}
