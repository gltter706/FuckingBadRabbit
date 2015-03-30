package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class FinishActivity extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_finish);
		ImageView iv_finish= (ImageView) findViewById(R.id.iv_finish);
		Animation animation = AnimationUtils.loadAnimation(FinishActivity.this, R.anim.anim_fade_out);
		iv_finish.setAnimation(animation);
		iv_finish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FinishActivity.this,StartActivity.class);
				startActivity(intent);
				FinishActivity.this.finish();
			}
		});
	}
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Intent intent = new Intent(FinishActivity.this,StartActivity.class);
		FinishActivity.this.startActivity(intent);
		FinishActivity.this.finish();
	}
}
