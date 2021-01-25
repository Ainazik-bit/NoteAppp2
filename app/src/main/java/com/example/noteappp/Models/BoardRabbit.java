package com.example.noteappp.Models;

public class BoardRabbit {

    private  String  name;
    private String desc;
    private int imageResourceId;

    public BoardRabbit(String name, String desc, int imageResourceId) {
        this.name = name;
        this.desc = desc;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}

