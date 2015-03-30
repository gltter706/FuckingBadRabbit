package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import shitGame.shitgame01.utils.Bag;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class PlaneControlActivity extends Activity{
	final int frameOfCartoon = 4; // 修改漫画总帧数
	private GestureDetector gestureDetector;
	private int counter = 0;
	private int[] cartoon_id = new int[frameOfCartoon];
	private ImageView iv_cartoon;
	private Bag bag;
	private long spend_time;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent getIntent = getIntent();
		spend_time = getIntent.getLongExtra("spend_time", 0xfffff);
		bag = (Bag) getIntent.getSerializableExtra("bag");
		setContentView(R.layout.layout_control);
		cartoon_id[0] = R.drawable.plane_control1;
		cartoon_id[1] = R.drawable.plane_control2;
		cartoon_id[2] = R.drawable.plane_control3;
		cartoon_id[3] = R.drawable.plane_control4;

		iv_cartoon = (ImageView) findViewById(R.id.iv_control);
		// this.init_pic();
		gestureDetector = new GestureDetector(PlaneControlActivity.this,
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
								Intent intent=new Intent(PlaneControlActivity.this, BossPlaneActivity.class);
								intent.putExtra("bag", bag);
								intent.putExtra("spend_time", spend_time);
								PlaneControlActivity.this.startActivity(intent);
								PlaneControlActivity.this.finish();
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
