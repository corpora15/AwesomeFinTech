package OCR;

/**
 * Created by Joshua on 7/29/2017.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TessOCR {
    private final TessBaseAPI mTess;
    private String datapath = "";

    public TessOCR(Context context, String language) {
        mTess = new TessBaseAPI();
        datapath = context.getFilesDir()+ "/";
        checkFile(new File(datapath + "tessdata/"), context);
        mTess.init(datapath, language);
    }

    public String getOCRResult(Bitmap bitmap) {
        mTess.setImage(bitmap); return mTess.getUTF8Text();
    }

    public void onDestroy() {
        if (mTess != null) mTess.end();
    }


    private void checkFile(File dir, Context context) {
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles(context);
        }
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);

            if (!datafile.exists()) {
                copyFiles(context);
            }
        }
    }

    private void copyFiles(Context context) {
        try {
            String filepath = datapath + "/tessdata/eng.traineddata";
            AssetManager assetManager = context.getAssets();

            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }

            outstream.flush();
            outstream.close();
            instream.close();

            File file = new File(filepath);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
