package com.example.projectmain.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.projectmain.login.SplashLogin;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    int Private_mode =0;

    public static final String PREF_NAME = "AndroidHivePref";

    public static final String IS_LOGGED_IN = "isLogginIn";
    public static final String KEY_NIK = "nik";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FULL_NAME = "full_name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";

    public Context get_context() {
        return _context;
    }

    //constructor
    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(PREF_NAME,Private_mode);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(String nik,String password) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(KEY_NIK,nik);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public boolean isLogginIN() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
    public void CheckLogin(){
        if (!this.isLogginIN()){
            Intent intent = new Intent(_context, SplashLogin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NIK, sharedPreferences.getString(KEY_NIK, null));
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        user.put(KEY_FULL_NAME, sharedPreferences.getString(KEY_FULL_NAME, null));
        user.put(KEY_PHONE, sharedPreferences.getString(KEY_PHONE, null));
        user.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));

        return user;
    }

    public void LogOutUser() {
        //clearing all data from shared Preferences
        editor.clear();
        editor.commit();

        Intent intent = new Intent(_context, SplashLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(intent);

    }

}
