package com.example.noteappp;

import android.app.Application;

import androidx.room.Room;

import com.example.noteappp.room.AppDatabase;

public class App extends Application {

    public static AppDatabase database;

    public static AppDatabase getAppDataBase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(
                this,
                AppDatabase.class,
                "database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries().
                        build();
    }

}

