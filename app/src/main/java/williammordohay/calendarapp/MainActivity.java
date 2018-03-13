package williammordohay.calendarapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {


    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    String downloadResult;

    // File url to download
    private static String file_url = "http://ade6-ujf-ro.grenet.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?resources=7265&projectId=2&calType=ical&firstDate=2018-02-12&lastDate=2018-02-17";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);




        //essai recup
        AssetManager assetManager = getAssets();

        try {
           InputStream files = assetManager.open("lpSmin.ics");
           //FileInputStream fin = new FileInputStream("mycalendar.ics");
           CalendarBuilder builder = new CalendarBuilder();
           Calendar calendar = builder.build(files);
            Log.d("READ ICS FILE", "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        } catch (IOException e) {
            Log.d("READ ICS FILE CATCH", "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        /*try {
            downloadResult = (String) new DownloadFileFromUrl().execute(file_url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

    }

    public String readFileAsString(String filePath)
            throws java.io.IOException {
        StringBuilder fileData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            fileData.append(buf, 0, numRead);
        }
        reader.close();
        return fileData.toString();
    }

    /**
     * Showing Dialog
     * */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }


}
