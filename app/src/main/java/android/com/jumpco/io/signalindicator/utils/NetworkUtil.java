package android.com.jumpco.io.signalindicator.utils;

import android.com.jumpco.io.signalindicator.SignalIndicatorApplication;
import android.com.jumpco.io.signalindicator.activities.MainActivity;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;

public class NetworkUtil {

    public final static String WIFI_SIGNAL_FOLDER = "wifi_signal_folder";
    public final static String WIFI_JSON_FILE = "wifi.txt";

    public final static String WIFI_JSON_FILE2 = "wifill.txt";
    // find the root of the external storage.
    public static void createWifiJsonFile(WifiModel wifiModel) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), WIFI_SIGNAL_FOLDER);
            root.mkdirs();
            // File root = android.os.Environment.getExternalFilesDir();
            JSONObject json = new JSONObject();
            json.put("name", wifiModel.name);
            json.put("wifiStrength", wifiModel.wifiStrength);
            final File dir = new File(root, WIFI_JSON_FILE2);
            if (!dir.exists())
                dir.mkdirs();
            //File file = new File(dir, SETTINGS_FILE);
            FileOutputStream os = new FileOutputStream(dir);
            os.write(json.toString().getBytes());
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // check internet
    public static boolean isNetworkAvailable(Context context){
        boolean isConnected = false;

        // GET network connection from Android
        ConnectivityManager connectionManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // GET list of networks available n Android
        NetworkInfo[] allNetworkInfo = connectionManager.getAllNetworkInfo();

        for (NetworkInfo networkInfoList : allNetworkInfo) {
            if(networkInfoList.getTypeName().equalsIgnoreCase("WIFI")){
                isConnected = true;
                Log.d("Check Internet ",isConnected+" " );
                return isConnected;
             //   Toast.makeText(context,"Network Available Wifi " +isConnected,Toast.LENGTH_SHORT).show();
            }
            else {
                isConnected = false;
                Log.d("Check Internet ",isConnected+" " );
            }

        }
        return isConnected;
    }

    // get storage directory and write data to the file
    public static String readDataFromJsonFile(String fileName) {
        File sdCardDir = new File(Environment.getExternalStorageDirectory(), NetworkUtil.WIFI_SIGNAL_FOLDER);

        // Get The Text file
        File txtFile = new File(sdCardDir, fileName);

        // Read the file Contents in a StringBuilder Object
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFile));
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line + '\n');
            }
            reader.close();
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "Error occured while reading text file!!");
        }
        return text.toString();
    }

    // write the list of wireless networks to a file
    public static void writeDatatoJsonFile(JSONArray jsonArray){
        File root = new File(Environment.getExternalStorageDirectory(), NetworkUtil.WIFI_SIGNAL_FOLDER);
        if (!root.exists()) {
            root.mkdirs();
        }
        File gpxfile = new File(root, NetworkUtil.WIFI_JSON_FILE);
        FileWriter writer = null;
        try {
            writer = new FileWriter(gpxfile);
            writer.append(jsonArray.toString());
            writer.flush();
            writer.close();
            Log.d("Write to File",jsonArray.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
