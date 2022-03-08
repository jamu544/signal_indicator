package android.com.jumpco.io.signalindicator.model;

import com.google.gson.annotations.SerializedName;

public class WifiModel {
    @SerializedName("name")
    public String name;
    @SerializedName("wifiStrength")
    public int wifiStrength;

    public WifiModel() {
    }

    public WifiModel(String name, int wifiStrength) {
        this.name = name;
        this.wifiStrength = wifiStrength;
    }

    @Override
    public String toString() {
        return
                name +
                ", " + wifiStrength;
    }


}
