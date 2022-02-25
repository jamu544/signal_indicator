package android.com.jumpco.io.signalindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public static String TAG = "MainActivity";
    private Context context;

    private TextView scanResults;
    private Button scanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

     //   isNetworkAvailable(context);
     //  scanButton();
    }

    // initialize components
    public void init(){
        context = this;
        scanResults = findViewById(R.id.scanResults);
        scanButton = findViewById(R.id.scanButton);
    }

    // check internet
    public boolean isNetworkAvailable(Context context){
        boolean isConnected = false;

        ConnectivityManager connectionManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] allNetworkInfo = connectionManager.getAllNetworkInfo();

        for (NetworkInfo networkInfoList : allNetworkInfo) {
            if(networkInfoList.getTypeName().equalsIgnoreCase("WIFI")){
                isConnected = true;
                Toast.makeText(context,"Network Available " +isConnected,Toast.LENGTH_SHORT).show();
            }
            else {
                isConnected = false;
                Toast.makeText(context,"No Network Available "+isConnected,Toast.LENGTH_SHORT).show();

            }

        }


        return isConnected;
    }

    // press button to scan wifi
    public void scanButton(){

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Test Button");
            }
        });

    }
}