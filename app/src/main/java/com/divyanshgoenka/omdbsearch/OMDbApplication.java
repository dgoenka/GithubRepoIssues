package com.divyanshgoenka.omdbsearch;


import android.app.Application;


/**
 * Created by divyanshgoenka on 25/03/17.
 */

public class OMDbApplication extends Application {
    private static OMDbApplication instance;

    public static OMDbApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
