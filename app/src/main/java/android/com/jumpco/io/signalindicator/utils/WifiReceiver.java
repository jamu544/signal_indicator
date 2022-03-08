package android.com.jumpco.io.signalindicator.utils;

import android.app.ProgressDialog;
import android.com.jumpco.io.signalindicator.SignalIndicatorApplication;
import android.com.jumpco.io.signalindicator.activities.MainActivity;
import android.com.jumpco.io.signalindicator.adapters.WifiRecyclerViewAdapter;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
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
    public WifiManager wifiManager;

    public RecyclerView wifiDeviceList;
    public WifiRecyclerViewAdapter arrayAdapter;




    public WifiReceiver(){

    }

    public WifiReceiver(WifiManager wifiManager, RecyclerView wifiDeviceList) {
        this.wifiManager = wifiManager;
        this.wifiDeviceList = wifiDeviceList;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equalsIgnoreCase(action)) {

           new BackgroundTask().execute();

        }
        }

    private  class BackgroundTask extends AsyncTask<Void,Void,Void> {

        private ProgressDialog progressDialog;


        /**
         * @deprecated
         */
        @Override
        protected void onPreExecute() {

//            progressDialog = new ProgressDialog(SignalIndicatorApplication.appContext);
//            progressDialog.setMessage("Loading networks...");
//            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... strings) {

            WifiModel pojo = new WifiModel();
            List<ScanResult> wifiList = wifiManager.getScanResults();
            ArrayList<WifiModel> deviceWifiList = new ArrayList<>();
            JSONArray data = new JSONArray();

            try {
                for (ScanResult scanResult : wifiList) {
                    JSONObject objectObje = new JSONObject();
                    pojo = new WifiModel();
                    pojo.name = scanResult.SSID;
                    pojo.wifiStrength = scanResult.level;

                    objectObje.put("name", pojo.name);
                    objectObje.put("wifiFrequency", pojo.wifiStrength);

                    data.put(objectObje);
                    deviceWifiList.add(pojo);
                }
                Log.v("Scan Results: ", deviceWifiList.toString());
                Log.v("Scan Results: in JSON  ", data.toString());

                arrayAdapter = new WifiRecyclerViewAdapter(SignalIndicatorApplication.appContext, deviceWifiList);

                NetworkUtil.writeDatatoJsonFile(data);//write data to json file

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

        /**
         * @param unused
         * @deprecated
         */
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
//            if (progressDialog != null)
//            {
//                progressDialog.dismiss();
//            }
            wifiDeviceList.setLayoutManager(new LinearLayoutManager(SignalIndicatorApplication.appContext));
            arrayAdapter.notifyDataStateChanged();

            wifiDeviceList.setAdapter(arrayAdapter);


        }
    }
}
