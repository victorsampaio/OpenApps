package open.com.openapps.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import open.com.openapps.mediaplayermp3.PlayerMp3;


/**
 * Created by VictorSampaio on 13/09/2014.
 */
public class ServiceMp3 extends Service implements InterfaceMp3 {
    private static final String CATEGORY = "serviceMp3";

    private PlayerMp3 player = new PlayerMp3();
    private final ConnectionInterfaceMp3 connection = new ConnectionInterfaceMp3();

    public class ConnectionInterfaceMp3 extends Binder {
        public InterfaceMp3 getService() {
            //Return the interface for control the service
            return ServiceMp3.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(CATEGORY, "ServiceMp3onBind(). return the IBinder");


        return null;
    }

    @Override
    public void onCreate(){
        Log.i(CATEGORY, "ServiceMp3 onCreate()");
    }

    @Override
    public void onStart(Intent intent, int startId){
        Log.i(CATEGORY, "ServiceMp3 onStart()");
    }

    //InterfaceMp3.start(mp3)
    public void start(String mp3){
        try {
            player.start(mp3);

        }catch (Exception e){
            Log.e(CATEGORY, e.getMessage(),e);
        }
    }

    // InterfaceMp3.pause
    public void pause(){
        player.pause();
    }

    public void stop(){
        player.stop();
    }

    public void onDestroy(){

        player.close();
    }




}
