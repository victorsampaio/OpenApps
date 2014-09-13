package open.com.openapps.mp3listcard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import open.com.openapps.R;
import open.com.openapps.mediaplayermp3.PlayerMp3;

public class ExampleMp3ListCard extends Activity implements OnItemClickListener {

    private static final String CATEGORY = "listCard";
    private String names[] = null;
    private PlayerMp3 player = new PlayerMp3();

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_example_mp3_list_card);

        File sdcardDir = android.os.Environment.getExternalStorageDirectory();
        Log.i(CATEGORY, "sdcard: " + sdcardDir.getAbsolutePath());

        // Open the path /mnt/sdcard
        if (sdcardDir.exists()){
            File[] files = sdcardDir.listFiles();
            names = new String[files.length];
            for (int i = 0 ; i < files.length ; i ++ ){
                names[i] = files[i].getAbsolutePath();
            }
        }

        // Recover the ListView
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        // Adapter list with the mp3 names
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(arrayAdapter);
    }

    /**
     * @see android.widget.AdapterView.OnItemClickListener#onItemClick(
     * android.widget.AdapterView, android.view.View, int, long)
     * */

    public void onItemClick (AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(this, names[position], Toast.LENGTH_SHORT).show();
        player.start(names[position]);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        player.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.example_mp3_list_card, menu);
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
