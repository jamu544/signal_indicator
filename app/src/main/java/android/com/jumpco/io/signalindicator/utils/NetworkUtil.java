package android.com.jumpco.io.signalindicator.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.sql.Connection;

public class NetworkUtil {

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


}
