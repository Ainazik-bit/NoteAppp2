package com.example.noteappp;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs(Context context){
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public boolean IsShown(){
        return preferences.getBoolean("isShown", false);
    }

    public void saveBoardStatus() {
        preferences.edit().putBoolean("isShown", true).apply();
    }

    public void clearSets(){
        preferences.edit().clear().apply();
    }
}
