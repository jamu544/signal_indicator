package android.com.jumpco.io.signalindicator.model;

public class WifiModel {
    public String name;
    public int wifiStrength;

    @Override
    public String toString() {
        return
                name +
                ", " + wifiStrength;
    }


}
