package shitGame.shitgame01.activities;

import shitGame.shitgame01.utils.Bag;
import shitGame.shitgame01.utils.ChallengeSurfaceView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ChallengeActivity extends Activity
{
	private TextView tv;
	private Bag bag;
	private ChallengeSurfaceView challengeSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*tv=new TextView(ChallengeActivity.this);
		Intent intent=getIntent();
		bag=(Bag) intent.getSerializableExtra("bag");
		if(bag!=null)
		{
		String cur_mission_num=""+bag.getMission();
		String selectString1=bag.getSelected_1().getItem_id();
		String selectString2=bag.getSelected_2().getItem_id();
		String selectString3=bag.getSelected_role().getItem_id();
		tv.setText("Cur_selected_mission_num:"+cur_mission_num+"\n"+"Selected_Item1:"+selectString1+"\n"+"Selected_Item2:"+selectString2+"\n"+"selected_role:"+selectString3);
		}
		else {
			tv.setText("null");
		}
		setContentView(tv);*/
		Intent intent=getIntent();
		bag=(Bag) intent.getSerializableExtra("bag");
		challengeSurfaceView=new ChallengeSurfaceView(ChallengeActivity.this, bag);
		setContentView(challengeSurfaceView);
		
	}
	@Override
	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		//super.onBackPressed();
	}

}
