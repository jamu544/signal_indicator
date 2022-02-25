package android.com.jumpco.io.signalindicator;

import android.app.Application;
import android.content.Context;

public class SignalIndicatorApplication extends Application {

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //Rquired initilization logic required here!
        appContext = this;
    }

}
