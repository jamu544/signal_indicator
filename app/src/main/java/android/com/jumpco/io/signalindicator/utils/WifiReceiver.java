package android.com.jumpco.io.signalindicator.utils;

import android.com.jumpco.io.signalindicator.SignalIndicatorApplication;
import android.com.jumpco.io.signalindicator.adapters.WifiRecyclerViewAdapter;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

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

            for (ScanResult scanResult : wifiList) {
                pojo = new WifiModel();
                pojo.name = scanResult.SSID;
                pojo.wifiStrength = scanResult.level;

                deviceWifiList.add(pojo);
            }
            Log.v("Scan Results: ",deviceWifiList.toString());

            WifiRecyclerViewAdapter arrayAdapter = new WifiRecyclerViewAdapter(SignalIndicatorApplication.appContext,deviceWifiList);
            wifiDeviceList.setLayoutManager(new LinearLayoutManager(SignalIndicatorApplication.appContext));
            arrayAdapter.notifyDataStateChanged();

            wifiDeviceList.setAdapter(arrayAdapter);

        }

    }
}
