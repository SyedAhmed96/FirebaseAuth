package com.example.ahmed.firebaseauth;

/**
 * Created by Ahmed on 1/15/2018.
 */

public class FriendlyMessage {
    private String text;
    private String email;
    private String photoUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String email) {
        this.text = text;
        this.email = email;
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.email = name;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
