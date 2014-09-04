package open.com.openapps.utils;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by VictorSampaio on 03/09/2014.
 */
public class IOUtils {

    private static final String TAG = "IOUtils";

    public static String toString (InputStream in, String charset)throws IOException{
        byte[] bytes = toBytes(in);
        String text = new String(bytes, charset);

        return text;
    }

    public static byte[] toBytes (InputStream in){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0){
                    bos.write(buffer, 0, len);
            }
            byte[] bytes = bos.toByteArray();
            return bytes;

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(),e);
            return null;
        } finally {
            try {
                bos.close();
                in.close();
            }catch (IOException e){
                Log.e(TAG, e.getMessage(),e);
            }
        }
    }
}
