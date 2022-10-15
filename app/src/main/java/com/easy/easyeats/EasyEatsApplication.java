package com.easy.easyeats;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class EasyEatsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        // TODO: what should we do in application's onCreate lify cycle
        // Set up some application-wise settings
        //  - initialize the database
        //  - register the debug tools - like flipper
    }
}
