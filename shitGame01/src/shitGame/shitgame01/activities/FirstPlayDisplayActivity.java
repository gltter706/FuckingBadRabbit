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


	long starttime=System.currentTimeMillis();//��¼��������ʱ��
	/*
	 * ��ò���currnetTimeMillis()������û��޸�ϵͳʱ���Ӱ����
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
        loadingImageView.setImageResource(R.drawable.anim_start_loading);
        
       /*���������������ɺ���Ӧ�������SelectMissionActivity*/
        loadingImageView.setOnClickListener(new OnClickListener() {
        	
        	ImageView loadingImageView=(ImageView)findViewById(R.drawable.anim_start_loading);
			@Override
			public void onClick(View v) {
				        /*����¿�ά����*/
				        /*animationDrawable�±���*/
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