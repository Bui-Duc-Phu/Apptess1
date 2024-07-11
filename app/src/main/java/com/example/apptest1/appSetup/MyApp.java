package com.example.apptest1.appSetup;


import android.app.Application;
import com.google.firebase.FirebaseApp;

public class MyApp extends Application {
     String uid = "";
     String token = "";

    @Override
    public void onCreate() {
        super.onCreate();



    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
