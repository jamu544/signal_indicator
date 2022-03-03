package android.com.jumpco.io.signalindicator.utils;

import android.com.jumpco.io.signalindicator.model.WifiModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WifiService {

    @POST("wifis")
    Call<WifiModel> createWifi(@Body WifiModel comment);


    //funtion to post data
   // Call<WifiModel> createPost(@Body WifiModel dataModal);
}
