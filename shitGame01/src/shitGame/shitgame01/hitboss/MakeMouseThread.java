package shitGame.shitgame01.hitboss;

import java.util.Random;

import shitGame.shitgame01.activities.BossHitActivity;
import shitGame.shitgame01.hitData.PublicData;


import android.os.Message;

public class MakeMouseThread extends Thread{
	
	private boolean runOrStop = true;
	
	public void setRunOrStop(boolean runOrStop){
		this.runOrStop = runOrStop;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(runOrStop){
		int random = new Random().nextInt(9);
		Message createMsg = BossHitActivity.makeMouseHandler.obtainMessage();
		createMsg.arg1 = random;
		createMsg.arg2 = 0;
		BossHitActivity.makeMouseHandler.sendMessage(createMsg);
		try {
			Thread.sleep(PublicData.makeMouseTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	}
}
