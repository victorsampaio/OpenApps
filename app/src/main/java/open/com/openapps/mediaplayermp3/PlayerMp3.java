package open.com.openapps.mediaplayermp3;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

/**
 * Created by VictorSampaio on 08/09/2014.
 */
public class PlayerMp3 implements OnCompletionListener{
    private static final String CATEGORY = "mp3player";
    private static final int NEW = 0;
    private static final int INITIATED = 1;
    private static final int PAUSED = 2;
    private static final int STOPED = 3;

    // Initial
    private int status = NEW;
    private MediaPlayer player;
    private String mp3;

    public PlayerMp3 (){
        // create new MediaPlayer
        player = new MediaPlayer();
        // Execute the Listener
        player.setOnCompletionListener(this);
    }

    public void start(String mp3){
        this.mp3 = mp3;

        try {
            switch (status){
                case INITIATED:
                    player.stop();

                case STOPED:
                    player.reset();

                case NEW:
                    player.setDataSource("");
                    player.prepare();

                case PAUSED:
                    player.start();
                    break;
            }
            status = INITIATED;

        } catch (Exception e){
            Log.e(CATEGORY, e.getMessage(),e);
        }
    }

    public void pause() {
       player.pause();
        status = PAUSED;
    }

    public void stop(){
        player.stop();
        status = STOPED;
    }

    // Close MediaPlayer and release the memory
    public void close(){
        stop();
        player.release();
        player = null;
    }

    /**
     * @see android.media.MediaPlayer.OnCompletionListener(android.media.MediaPlayer)
     */
    @Override
    public void onCompletion(MediaPlayer mp) {
    // finis the player
        Log.i(CATEGORY, "End Music: " + mp3);
    }
}
