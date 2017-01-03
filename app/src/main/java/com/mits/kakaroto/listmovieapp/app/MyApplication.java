package com.mits.kakaroto.listmovieapp.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.mits.kakaroto.listmovieapp.model.Movie;
import com.mits.kakaroto.listmovieapp.model.User;
import com.mits.kakaroto.listmovieapp.utility.SessionManager;

/**
 * Created by sunari on 29/12/16.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SessionManager.init(this);

        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(Movie.class, User.class);
        ActiveAndroid.initialize(this);
    }
}
