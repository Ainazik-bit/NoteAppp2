package com.example.noteappp.Models;

import java.io.Serializable;

public class Note implements Serializable {

    private String titles;

    public Note(String text) {
        titles = text;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    
}
