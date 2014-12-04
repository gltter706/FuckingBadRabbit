package shitGame.shitgame01.services;
import shitGame.shitgame01.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
 
public class PlayMusicService extends Service {
    private MediaPlayer mp;
 
    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this,R.raw.msc_startmenu);
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        stopSelf();
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean playing = intent.getBooleanExtra("playing", false);
        if (playing) {
            mp.start();
        } else {
            mp.pause();
        }
        return super.onStartCommand(intent, flags, startId);
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 
}
