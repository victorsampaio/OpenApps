package open.com.openapps;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import open.com.openapps.opencam.Camera;
import open.com.openapps.openmusic.MusicPlayers;
import open.com.openapps.openmusicsdcard.MusicPlayerSDCard;
import open.com.openapps.transitionscreen.SecondScreen;

public class MenuOpenApps extends ListActivity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.activity_menu_open_apps);

        String[] topic = new String[]{"1 - Transition Screen","2 - Open Cam","3 - Player Music","4 - Player Music SDCard","5","6"};

        this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, topic));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        try {
            switch (position) {
                case 0:
                        Intent itTransition = new Intent(this, SecondScreen.class);
                        startActivity(itTransition);
                    break;

                case 1:
                        Intent itCam = new Intent(this, Camera.class);
                        startActivity(itCam);
                    break;

                case 2:
                    /** Open Music Player using 'raw'
                    * */
                        Intent itPlayer = new Intent(this, MusicPlayers.class);
                        startActivity(itPlayer);
                    break;

                case 3:
                    /** Open Music Player using 'SD CARD'
                     *
                     */
                        Intent itPlayerSDCard = new Intent(this, MusicPlayerSDCard.class);
                        startActivity(itPlayerSDCard);

                    break;
                case 4:

                    break;
                case 5:

                    break;

                case 6:

                    break;
                default:
                    finish();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Erro :" + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();

        }

    }




        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_apps, menu);
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
