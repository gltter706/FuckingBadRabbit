package shitGame.shitgame01.services;
import java.io.IOException;

import shitGame.shitgame01.R;
import shitGame.shitgame01.activities.StartActivity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.util.LogPrinter;
 
public class PlayMusicService extends Service {
    private MediaPlayer mp;
 
    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(getApplicationContext(), R.raw.msc_startmenu);
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        stopSelf();
    }
 
    @Override
    public void onStart(Intent intent, int startId) {
        boolean playing = intent.getBooleanExtra("msc_playing",true);
        if (playing) {
        	try {
				mp.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            mp.start();
        } else {
            mp.pause();
        }
        Log.i("error","onmusic");
  }
 
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 
}
