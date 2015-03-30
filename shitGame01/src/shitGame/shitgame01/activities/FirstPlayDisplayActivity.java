package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class FirstPlayDisplayActivity extends Activity {

	final int frameOfCartoon=6;          //修改漫画总帧数
	private GestureDetector gestureDetector;
	int counter=0;
	private int[] cartoon_id = new int[frameOfCartoon];
	private ImageView iv_cartoon;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_start_firstplay);
		cartoon_id[0] = R.drawable.bg_cartoon_firstplay1;
		cartoon_id[1] = R.drawable.bg_cartoon_firstplay2;
		cartoon_id[2] = R.drawable.bg_cartoon_firstplay3;
		cartoon_id[3] = R.drawable.bg_cartoon_firstplay4;
		cartoon_id[4] = R.drawable.bg_cartoon_firstplay5;
		cartoon_id[5] = R.drawable.bg_cartoon_firstplay6;
		
		iv_cartoon = (ImageView) findViewById(R.id.layout_first_play);
		// this.init_pic();
		gestureDetector = new GestureDetector(FirstPlayDisplayActivity.this,
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
							if (counter < frameOfCartoon-1) {
								counter++;
								((BitmapDrawable)(iv_cartoon.getDrawable())).getBitmap().recycle();
								iv_cartoon.setImageResource(cartoon_id[counter]);
//							    layout.setBackground(cartoonDrawables[counter]);
							} else {
								Intent intent = new Intent(
										FirstPlayDisplayActivity.this,
										SelectMissionActivity.class);
								FirstPlayDisplayActivity.this
										.startActivity(intent);
								FirstPlayDisplayActivity.this.finish();
							}

						}
						return false;
					}
				});

	}

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);

	}


}