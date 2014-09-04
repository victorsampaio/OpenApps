package open.com.openapps.opencam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.provider.MediaStore;

import java.io.File;
import java.net.URI;

import open.com.openapps.R;
import open.com.openapps.utils.SDCardUtils;
import open.com.openapps.utils.IOUtils;
import open.com.openapps.utils.ImageUtils;

public class Camera extends Activity {

    // directory to the photo
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageButton btnCam = (ImageButton)findViewById(R.id.btOpenCam);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Create the directory of archive on the sdcard
                file = SDCardUtils.getSDCardFile("cam", "foto.jgp");

                Intent itCam = new Intent("android.media.action.IMAGE_CAPTURE");
                itCam.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
                startActivityForResult(itCam, 0);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        /**
        if (data != null){

            Bundle bundle = data.getExtras();

            if (bundle != null) {

                Bitmap bitmap = (Bitmap)bundle.get("data");

                ImageView img = (ImageView)findViewById(R.id.image);
                img.setImageBitmap(bitmap);
            }
        }*/

        if (resultCode == RESULT_OK){
            Bitmap bitmap = ImageUtils.getResizedImage(Uri.fromFile(file),250,250);

            ImageView img = (ImageView)findViewById(R.id.image);
            img.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera, menu);
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
