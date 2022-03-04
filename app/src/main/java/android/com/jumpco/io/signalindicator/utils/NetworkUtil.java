package android.com.jumpco.io.signalindicator.utils;

import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;

public class NetworkUtil {



    public final static String WIFI_SIGNAL_FOLDER = "/wifi_signal_folder";
    public final static String WIFI_JSON_FILE = "wifi.json";

    // Find the root of the external storage.
    // See http://developer.android.com/guide/topics/data/data-
    // storage.html#filesExternal
    public static void createWifiJsonFile(WifiModel wifiModel) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), WIFI_SIGNAL_FOLDER);
            // File root = android.os.Environment.getExternalFilesDir();
            JSONObject json = new JSONObject();
            json.put("name", wifiModel.getName());
            json.put("wifiStrength", wifiModel.getWifiStrength());
            // See
            // http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder
            final File dir = new File(root, WIFI_JSON_FILE);
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

    // read wifi json file from external Storage
    public static WifiModel readFromSDFile() {
        WifiModel wifiObject = new WifiModel();
        String data = null;
        try {
            File root = new File(Environment.getExternalStorageDirectory(), WIFI_SIGNAL_FOLDER);
            File dir = new File(root, WIFI_JSON_FILE);
            InputStream jsonStream;

            if (dir.exists()) {
                jsonStream = new FileInputStream(dir);
                data = InputStreamToString(jsonStream);
            } else
                return wifiObject;

        } catch (Exception e) {
            return wifiObject;
        }

        try {
            wifiObject = new WifiModel();
            JSONObject job = new JSONObject(data);
            if (job.has("name")) {
                wifiObject.setName(job.getString("name"));
            }
            if (job.has("wifiStrength")) {
                wifiObject.setWifiStrength(job.getInt("wifiStrength"));
            }

        } catch (JSONException e) {
            wifiObject = null;
            return wifiObject;
        }
        return wifiObject;
    }
//
//    public static Boolean checkFile() {
//        try {
//            // Find the root of the external storage.
//            // See http://developer.android.com/guide/topics/data/data-
//            // storage.html#filesExternal
//            File root = android.os.Environment.getExternalStorageDirectory();
//            File dir = new File(root.getAbsolutePath() + WIFI_SIGNAL_FOLDER);
//            if (!dir.exists())
//                dir.mkdirs();
//
//            File file = new File(dir, WIFI_JSON_FILE);
//            if (!file.exists()){
//                file.createNewFile();
//                return false;
//            }else{
//                return true;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//


    private static String InputStreamToString(InputStream is) {

        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
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
                Toast.makeText(context,"Network Available Wifi " +isConnected,Toast.LENGTH_SHORT).show();
            }
            else if (networkInfoList.getTypeName().equalsIgnoreCase("MOBILE")){
                isConnected = true;
                Toast.makeText(context,"Network Available MOBILE " +isConnected,Toast.LENGTH_SHORT).show();
            }
            else {
                isConnected = false;
                Toast.makeText(context,"No Network Available "+isConnected,Toast.LENGTH_SHORT).show();
            }
        }
        return isConnected;
    }



    //reading json file from assets folder
    public static String getJsonFromAssets(Context context, String fileName){
        String jsonString = null;

        try {

            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");

        }catch (IOException io){
            io.getStackTrace();

            return null;
        }

        return jsonString;

    }


    //parse json file and create a list of cities
    public static ArrayList<WifiModel> getListOfWifis(String jsonFileString){
        ArrayList<WifiModel> listOfWifis = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonFileString);

            JSONArray jsonArray = jsonObject.getJSONArray( "wifilist");

            for (int i = 0; i < jsonArray.length(); i++){

                JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                String name = jsonObject2.getString("name");
                int wifiStrength = jsonObject2.getInt("wifiStrength");


                listOfWifis.add(new WifiModel(name,wifiStrength));
            }

            return listOfWifis;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
