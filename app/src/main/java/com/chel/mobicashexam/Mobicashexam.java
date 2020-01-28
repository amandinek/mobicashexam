package com.chel.mobicashexam;


import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class Mobicashexam extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
