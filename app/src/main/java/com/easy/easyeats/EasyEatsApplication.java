package com.easy.easyeats;

import android.app.Application;

import androidx.room.Room;

import com.easy.easyeats.database.EasyEatsDatabase;
import com.facebook.stetho.Stetho;

public class EasyEatsApplication extends Application {
    private static EasyEatsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        // TODO: what should we do in application's onCreate lifecycle
        // Set up some application-wise settings
        //  - initialize the database
        //  - register the debug tools - like flipper
        database = Room.databaseBuilder(this, EasyEatsDatabase.class, "easyeats_db").build();
    }

    public static EasyEatsDatabase getDatabase() {
        return database;
    }
}
