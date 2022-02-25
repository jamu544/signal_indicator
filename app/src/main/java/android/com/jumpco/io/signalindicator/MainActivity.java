package android.com.jumpco.io.signalindicator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        isNetworkAvailable(context);
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
}