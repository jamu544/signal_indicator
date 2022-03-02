package android.com.jumpco.io.signalindicator.model;

public class WifiModel {
    private String name;
    private int wifiStrength;

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
