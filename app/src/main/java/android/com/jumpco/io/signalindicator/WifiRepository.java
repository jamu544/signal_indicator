package android.com.jumpco.io.signalindicator;

import android.com.jumpco.io.signalindicator.utils.WifiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


// Setting Up the HTTP Client
public final class WifiRepository {

    private static WifiRepository instance;

    private WifiService wifiService;

    public  WifiRepository getInstance() {
        if (instance == null) {
            instance = new WifiRepository();
        }
        return instance;
    }

    public WifiRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        wifiService = retrofit.create(WifiService.class);
    }

    public WifiService getWifiService() {
        return wifiService;
    }

}
