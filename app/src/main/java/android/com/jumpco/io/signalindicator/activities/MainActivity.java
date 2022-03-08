package android.com.jumpco.io.signalindicator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.com.jumpco.io.signalindicator.adapters.WifiRecyclerViewAdapter;
import android.com.jumpco.io.signalindicator.model.WifiModel;
import android.com.jumpco.io.signalindicator.utils.NetworkUtil;
import android.com.jumpco.io.signalindicator.R;
import android.com.jumpco.io.signalindicator.SignalIndicatorApplication;
import android.com.jumpco.io.signalindicator.utils.WifiReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    public static String TAG = "MainActivity";
    private Context context;

    private Button scanButton;
    private Button postDataButton;

//    private List<String> wifiList;
    private WifiManager wifiManager;
    private final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 1;
    private WifiReceiver receiverWifi;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        NetworkUtil.isNetworkAvailable(context);



    }
    // initialize components
    public void init(){
        context = SignalIndicatorApplication.appContext;
        recyclerView = findViewById(R.id.scanResultsList);
        scanButton = findViewById(R.id.scanButton);
        postDataButton = findViewById(R.id.postButton);
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @Override
    protected void onResume() {
        super.onResume();
        receiverWifi = new WifiReceiver(wifiManager, recyclerView);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(receiverWifi, intentFilter);

            scanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scanButton(view);
                }
            });

            postDataButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postDatainJsonFormat(view);
                }
            });
        }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiverWifi);
    }

    // press button to scan wifi list
    public void scanButton( View view){
        checkPermissionsAndStartScan();
    }
    // get required permissions and runtime and scan
    private void checkPermissionsAndStartScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(MainActivity.this, "version> = marshmallow", Toast.LENGTH_SHORT).show();
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_ACCESS_COARSE_LOCATION);
            } else {
                wifiManager.startScan();
            }
        } else {

            wifiManager.startScan();
        }
    }

    // POST data to the server
    public void postDatainJsonFormat(View view) {
        //NetworkUtil.readFromSDFile();
        Log.d("JSON POST", NetworkUtil.readDataFromJsonFile(NetworkUtil.WIFI_JSON_FILE)+" check " );
        Toast.makeText(MainActivity.this, "POST"+NetworkUtil.readDataFromJsonFile("WIFI_JSON_FILE.txt") , Toast.LENGTH_SHORT).show();
    }

}