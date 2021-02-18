package com.example.noteappp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteappp.Models.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ")
    List<Note> getAll();

    @Insert
    void insert(Note note);

    @Delete
    void delete (Note note);

    @Update
    void update(Note note);

    @Query("SELECT * FROM note ORDER BY titles")
    List<Note> getAllByName();

    @Query("SELECT * FROM note ORDER BY date")
    List<Note> getAllByDateASC();

    @Query("SELECT * FROM note ORDER BY date desc")
    List<Note> getAllByDateDESC();
}
