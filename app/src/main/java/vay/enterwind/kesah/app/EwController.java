package vay.enterwind.kesah.app;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by novay on 13/07/18.
 */

public class EwController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }

}
