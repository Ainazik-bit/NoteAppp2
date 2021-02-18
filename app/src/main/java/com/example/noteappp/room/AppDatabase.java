package com.example.noteappp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.noteappp.Models.Note;

@Database(entities = {Note.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
