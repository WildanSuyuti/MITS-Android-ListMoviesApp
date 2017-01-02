package com.mits.kakaroto.listmovieapp.app;

import android.app.Application;

import com.mits.kakaroto.listmovieapp.database.DatabaseHandler;
import com.mits.kakaroto.listmovieapp.utility.SessionManager;

/**
 * Created by sunari on 29/12/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionManager.init(this);
        DatabaseHandler.init(this);

    }
}
