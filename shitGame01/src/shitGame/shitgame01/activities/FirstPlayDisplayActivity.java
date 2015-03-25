package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class FirstPlayDisplayActivity extends Activity {

	final String TAG = "firstplayactivity";
	final int frameOfCartoon=4;          //修改漫画总帧数
	private GestureDetector gestureDetector;
	int counter=0;
	private LinearLayout layout;
	long starttime = System.currentTimeMillis();// 记录动画创建时间

	/*
	 * 最好不用currnetTimeMillis()，如果用户修改系统时间会影响结果
	 */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_start_firstplay);
		layout = (LinearLayout) findViewById(R.id.layout_first_play);
		// this.init_pic();
		Log.d(TAG, "init");
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
						// TODO Auto-generated method stub
						Log.d(TAG, "fling");
						if (e2.getX() - e1.getX() < 0) {
							if (counter < frameOfCartoon-1) {
								Log.d(TAG, "flingright");
								counter++;
							} else {
								Log.d(TAG, "flingright_finish");
								Intent intent = new Intent(
										FirstPlayDisplayActivity.this,
										SelectMissionActivity.class);
								FirstPlayDisplayActivity.this
										.startActivity(intent);
								FirstPlayDisplayActivity.this.finish();
							}

						} else if (e2.getX() - e1.getX() >0 && counter > 0) {
							Log.d(TAG, "flingleft");
								counter--;
						}
						layout.setBackgroundResource(R.drawable.bg_cartoon_firstplay1
								+ counter);
						return false;
					}
				});

	}

	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gestureDetector.onTouchEvent(event);

	}
	// private void init_pic(){
	// ImageView
	// loadingImageView=(ImageView)findViewById(R.id.imageView_loading);
	// loadingImageView.setImageResource(R.anim.anim_start_loading);
	//
	// /*监听点击，播放完成后相应点击返回SelectMissionActivity*/
	// loadingImageView.setOnClickListener(new OnClickListener() {
	//
	// ImageView
	// loadingImageView=(ImageView)findViewById(R.anim.anim_start_loading);
	// @Override
	// public void onClick(View v) {
	// /*需更新可维护性*/
	// /*animationDrawable致崩溃*/
	// if(System.currentTimeMillis()-starttime>6000){
	// Intent intent=new
	// Intent(FirstPlayDisplayActivity.this,SelectMissionActivity.class);
	// FirstPlayDisplayActivity.this.startActivity(intent);
	// FirstPlayDisplayActivity.this.finish();
	//
	//
	// }
	// }
	// });
	//
	// AnimationDrawable animationDrawable;
	// animationDrawable=(AnimationDrawable)loadingImageView.getDrawable();
	// animationDrawable.start();
	// }

}