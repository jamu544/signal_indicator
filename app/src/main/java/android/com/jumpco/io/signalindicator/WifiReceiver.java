package android.com.jumpco.io.signalindicator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// broadcast receiver will keep track of when internet changes

public class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;

    WifiConnectionPojo pojo;

    ListView wifiDeviceList;
    public WifiReceiver(WifiManager wifiManager,  ListView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
         String action = intent.getAction();

        if(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equalsIgnoreCase(action)){

            List<ScanResult> wifiList = wifiManager.getScanResults();
            ArrayList<WifiConnectionPojo> deviceWifiList = new ArrayList<>();

            for (ScanResult scanResult : wifiList) {
                pojo = new WifiConnectionPojo();
                pojo.name = scanResult.SSID;
                pojo.wifiStrength = scanResult.level;

                deviceWifiList.add(pojo);
            }
            Log.v("Scan Results: ",deviceWifiList.toString());

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, deviceWifiList);
            wifiDeviceList.setAdapter(arrayAdapter);

        }

    }
}
