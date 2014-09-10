package open.com.openapps.mediaplayermp3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import open.com.openapps.R;

public class ExampleMediaPlayer extends Activity implements OnClickListener {
    private static final String CATEGORY = "playermp3";

    private PlayerMp3 player = new PlayerMp3();
    private ImageButton btStart;
    private ImageButton btPause;
    private ImageButton btStop;
    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_media_player);

        text = (EditText) findViewById(R.id.Archive);

        btStart = (ImageButton) findViewById(R.id.start);
        btStart.setOnClickListener(this);

        btPause = (ImageButton) findViewById(R.id.pause);
        btPause.setOnClickListener(this);

        btStop = (ImageButton) findViewById(R.id.stop);
        btStop.setOnClickListener(this);
    }

    /**
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */

    @Override
    public void onClick(View view) {
        try {
            if (view == btStart) {
                String mp3 = text.getText().toString();
                player.start(mp3);
            } else if (view == btPause) {
                player.pause();
            } else if (view == btStop) {
                player.stop();
            }
        } catch (Exception e) {

            Log.e(CATEGORY, e.getMessage(), e);
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    protected void onDestroy(){
        super.onDestroy();

        player.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.example_media_player, menu);
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
