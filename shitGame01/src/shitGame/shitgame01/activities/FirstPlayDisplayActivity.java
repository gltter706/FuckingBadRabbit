package shitGame.shitgame01.activities;

import java.sql.Date;

import shitGame.shitgame01.R;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.ImageView;

public class FirstPlayDisplayActivity extends Activity {


	long starttime=System.currentTimeMillis();//记录动画创建时间
	/*
	 * 最好不用currnetTimeMillis()，如果用户修改系统时间会影响结果
	 * 
	 */
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_start_firstplay);
        this.init_pic();


            
    }
    
    
    private void init_pic(){
        ImageView loadingImageView=(ImageView)findViewById(R.id.imageView_loading);
        loadingImageView.setImageResource(R.anim.anim_start_loading);
        
        /*监听点击，播放完成后相应点击返回SelectMissionActivity*/
        loadingImageView.setOnClickListener(new OnClickListener() {
        	
        	ImageView loadingImageView=(ImageView)findViewById(R.anim.anim_start_loading);
			@Override
			public void onClick(View v) {
				 /*需更新可维护性*/
		        /*animationDrawable致崩溃*/
						if(System.currentTimeMillis()-starttime>6000){
				        Intent intent=new Intent(FirstPlayDisplayActivity.this,SelectMissionActivity.class);
						FirstPlayDisplayActivity.this.startActivity(intent);			
						FirstPlayDisplayActivity.this.finish();
						
						
						}
			}
		});

        AnimationDrawable animationDrawable;
        animationDrawable=(AnimationDrawable)loadingImageView.getDrawable();
        animationDrawable.start();
    }
    
 
}