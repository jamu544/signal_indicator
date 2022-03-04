package android.com.jumpco.io.signalindicator.utils;

import android.com.jumpco.io.signalindicator.SignalIndicatorApplication;
import android.com.jumpco.io.signalindicator.activities.MainActivity;
import android.com.jumpco.io.signalindicator.adapters.WifiRecyclerViewAdapter;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// broadcast receiver will keep track of when internet changes

public class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    WifiModel pojo;
    RecyclerView wifiDeviceList;

    public WifiReceiver(WifiManager wifiManager,  RecyclerView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
         String action = intent.getAction();

        if(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equalsIgnoreCase(action)){

            List<ScanResult> wifiList = wifiManager.getScanResults();
            ArrayList<WifiModel> deviceWifiList = new ArrayList<>();

            JSONArray data = new JSONArray();

            try {
                for (ScanResult scanResult : wifiList) {
                    JSONObject objectObje = new JSONObject();
                    pojo = new WifiModel();
                    pojo.setName(scanResult.SSID);
                    pojo.setWifiStrength(scanResult.level);

                    objectObje.put("name", pojo.getName());
                    objectObje.put("wifiFrequency", pojo.getWifiStrength());
//
                    data.put(objectObje);
                    deviceWifiList.add(pojo);

                }
                Log.v("Scan Results: ", deviceWifiList.toString());
                Log.v("Scan Results: in JSON  ", data.toString());

                WifiRecyclerViewAdapter arrayAdapter = new WifiRecyclerViewAdapter(SignalIndicatorApplication.appContext, deviceWifiList);
                wifiDeviceList.setLayoutManager(new LinearLayoutManager(SignalIndicatorApplication.appContext));
                arrayAdapter.notifyDataStateChanged();

                wifiDeviceList.setAdapter(arrayAdapter);
                NetworkUtil.createWifiJsonFile(pojo);


                File root = new File(Environment.getExternalStorageDirectory(), NetworkUtil.WIFI_SIGNAL_FOLDER);
                if (!root.exists()) {
                    root.mkdirs();
                }
                File gpxfile = new File(root, NetworkUtil.WIFI_JSON_FILE);
                FileWriter writer = new FileWriter(gpxfile);
                writer.append(data.toString());
                writer.flush();
                writer.close();
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
            }catch (JSONException r) {
                r.getMessage();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
