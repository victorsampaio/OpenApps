package open.com.openapps.openmusicsdcard;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import open.com.openapps.R;

public class MusicPlayerSDCard extends Activity implements OnClickListener{

    private static final String CATEGORY = "playerSDCard";
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_sdcard);

        Button btnPlaySDCard = (Button)findViewById(R.id.btPlaySDCard);
        btnPlaySDCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        try {
            player = new MediaPlayer();
            player.setDataSource("/mnt/sdcard/comedy_drum.mp3");
            player.prepare();
            player.start();
        }catch (Exception e){
            Log.e(CATEGORY, e.getMessage(),e);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (player != null){
            player.stop();
            player.release();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music_player_sdcard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
