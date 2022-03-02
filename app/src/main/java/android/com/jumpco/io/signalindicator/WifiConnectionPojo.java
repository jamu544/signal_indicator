package android.com.jumpco.io.signalindicator;

public class WifiConnectionPojo {
    public String name;
    public int wifiStrength;

    @Override
    public String toString() {
        return
                name +
                ", " + wifiStrength;
    }


}
