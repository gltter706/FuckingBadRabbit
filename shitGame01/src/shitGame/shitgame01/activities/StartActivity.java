package shitGame.shitgame01.activities;

import shitGame.shitgame01.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_menu);
//music initation
		Media media = new Media();
//����btn_start
		Button bt_start=(Button)findViewById(R.id.btn_start);
		bt_start.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * ȱ����¼�ж��Ƿ��һ�ν�����Ϸ
				 * */
				Intent intent=new Intent(StartActivity.this,FirstPlayDisplayActivity.class);
				startActivity(intent);
				
			}
		});
		
//����btn_shop
		Button bt_shop=(Button)findViewById(R.id.btn_shop);
		bt_shop.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(StartActivity.this,ShopActivity.class);
				startActivity(intent);				
			}
		});
//����btn_exit
		Button bt_exit=(Button)findViewById(R.id.btn_exit);
		bt_exit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();				
			}
		});
//����btn_music
				Button bt_music=(Button)findViewById(R.id.btn_music);
				bt_music.setOnClickListener(new OnClickListener(){
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						finish();	
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return false;
	}


}