package com.mits.kakaroto.listmovieapp.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sunari on 29/12/16.
 */

public class SessionManager {

    private static final String AUTH_PREFERENCES = "auth_preferences";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String GENDER = "gender";
    private static final String PASSWORD = "password";
    public static final String ISLOGGEDIN = "isloggedin";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static SessionManager instance;

    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        instance = new SessionManager(context);
    }

    public synchronized static SessionManager getInstance() {
        return instance;
    }

    public void setLogin(String email, String pass) {
        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, pass);
        editor.putBoolean(ISLOGGEDIN, true);
        editor.apply();
    }

    public String getEmail(){
        String email = sharedPreferences.getString(EMAIL, "");
        return email;
    }

    public String getPass(){
        String password = sharedPreferences.getString(PASSWORD, "");
        return password;
    }


    public boolean isLoggedin() {
        return sharedPreferences.getBoolean(ISLOGGEDIN, false);
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }
}
