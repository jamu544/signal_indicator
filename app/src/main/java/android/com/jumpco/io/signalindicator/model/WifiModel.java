package android.com.jumpco.io.signalindicator.model;

import com.google.gson.annotations.SerializedName;

public class WifiModel {
    @SerializedName("name")
    private String name;
    @SerializedName("wifiStrength")
    private int wifiStrength;

    public WifiModel() {
    }

    public WifiModel(String name, int wifiStrength) {
        this.name = name;
        this.wifiStrength = wifiStrength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWifiStrength() {
        return wifiStrength;
    }

    public void setWifiStrength(int wifiStrength) {
        this.wifiStrength = wifiStrength;
    }

    @Override
    public String toString() {
        return
                name +
                ", " + wifiStrength;
    }


}
