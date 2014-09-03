package open.com.openapps.transitionscreen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import open.com.openapps.R;

public class SecondScreen extends Activity {

    private static final String TAG = "transition";

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //setContentView(R.layout.activity_second_screen);

        Bundle extras = getIntent().getExtras();

        if (extras != null){

            String message = extras.getString("message");

            TextView text = new TextView(this);
            text.setText("The message is: " + message);
            setContentView(text);
        }else {

            TextView text = new TextView(this);
            text.setText("This is Activity Two");
            setContentView(text);

        }

    }

    protected void onCreate() {
        super.onStart();
        Log.i(TAG, "SecondScreen .onCreate() executed.");
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "SecondScreen .onStart() executed.");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "SecondScreen .onRestart() executed.");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "SecondScreen .onResume() executed.");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "SecondScreen .onPause() executed.");
    }

    protected void onStop() {
        super.onStop();
        Log.i(TAG, "SecondScreen .onStop() executed.");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "SecondScreen .onDestroy() executed.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_screen, menu);
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
