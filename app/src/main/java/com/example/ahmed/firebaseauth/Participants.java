package com.example.ahmed.firebaseauth;

/**
 * Created by Ahmed on 1/15/2018.
 */

public class Participants {
    private String email1;
    private String email2;

    public Participants(String email1, String email2) {
        this.email1 = email1;
        this.email2 = email2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

}
