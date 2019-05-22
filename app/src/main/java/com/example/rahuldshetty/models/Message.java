package com.example.rahuldshetty.models;

import android.graphics.Bitmap;

public class Message {

    private String text;
    private Bitmap image;
    private String type; // image / text
    private String from; // <others> / self

    public Message(String text, Bitmap image, String type, String from) {
        this.text = text;
        this.image = image;
        this.type = type;
        this.from = from;
    }

    public Message(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
