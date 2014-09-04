package open.com.openapps.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by VictorSampaio on 03/09/2014.
 */
public class ImageUtils {

    public static final String TAG = ImageUtils.class.getName();

    public static Bitmap getResizedImage (Uri uriFile, int width, int height){

        try {

            // Upload original image in memory
            Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath() );

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            float scaleX = (float)width / bitmap.getWidth();
            float scaleY = (float)height / bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.setScale(scaleX, scaleY);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0 , w, h, matrix,true);

            resizedBitmap = fixMatrix(uriFile, resizedBitmap);

            //clean the memory of the original archive
            bitmap.recycle();
            bitmap = null;

            // return image with resize
            return resizedBitmap;
        }catch (RuntimeException e){
            Log.e(TAG, e.getMessage(),e);
        }catch (IOException e){
            Log.e(TAG,e.getMessage(),e);
        }
            return null;
    }


        public static Bitmap getResizedImage2(Uri uriFile, int width, int height){
            try {

                // configure the BitmapFactory for read the size to the image
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;

                // decode of the image
                BitmapFactory.decodeFile(uriFile.getPath(),opts);

                //Read width and height of the image
                int w = opts.outWidth;
                int h = opts.outHeight;

                //Factor of the scale
                opts.inSampleSize = Math.min(w / width, h / height);

                // Load the complete bitmap
                opts.inJustDecodeBounds = false;

                Bitmap bitmap = BitmapFactory.decodeFile(uriFile.getPath(),opts);

                Bitmap newBitmap = fixMatrix(uriFile,bitmap);

                bitmap.recycle();

                return newBitmap;

            }catch (RuntimeException e){
                Log.e(TAG,  e.getMessage(),e);
            }catch (IOException e){
                Log.e(TAG, e.getMessage(), e);
            }
            return null;
    }

    public static Bitmap fixMatrix (Uri uriFile, Bitmap bitmap) throws IOException{

        Matrix matrix = new Matrix();

        // Class for read tags JPGE
        ExifInterface exif = new ExifInterface(uriFile.getPath());

        // Read orientation saved at the photo
        int orientation = exif.getAttributeInt( ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        boolean fix = false;

        // Rotate bitmap
        switch (orientation){
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fix = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fix = true;
                break;
            default:
                // ORIETATION_ROTATE_0
                fix = false;
                break;
        }

        if (!fix){
            return bitmap;
        }

        // Correction the orientation
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }
}
