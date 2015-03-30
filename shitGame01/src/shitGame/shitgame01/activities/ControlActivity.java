package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ControlActivity extends Activity {
	final int frameOfCartoon = 6; // 修改漫画总帧数
	private GestureDetector gestureDetector;
	int counter = 0;
	int cur_mission;
	private int[] cartoon_id = new int[frameOfCartoon];
	private ImageView iv_cartoon;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent get_missionIntent = getIntent();
		cur_mission = get_missionIntent.getIntExtra("cur_selected_mission", 0);
		setContentView(R.layout.layout_control);
		cartoon_id[0] = R.drawable.control_des1;
		cartoon_id[1] = R.drawable.control_des2;
		cartoon_id[2] = R.drawable.control_des3;
		cartoon_id[3] = R.drawable.control_des4;
		cartoon_id[4] = R.drawable.control_des5;
		cartoon_id[5] = R.drawable.control_des6;

		iv_cartoon = (ImageView) findViewById(R.id.iv_control);
		// this.init_pic();
		gestureDetector = new GestureDetector(ControlActivity.this,
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
								Intent intent=new Intent(ControlActivity.this, SelectItemActivity.class);
								intent.putExtra("cur_selected_mission",cur_mission);
								ControlActivity.this.startActivity(intent);
								ControlActivity.this.finish();
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
