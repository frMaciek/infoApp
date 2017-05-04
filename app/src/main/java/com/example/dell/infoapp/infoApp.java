package com.example.dell.infoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

public class infoApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);
    }

    /**
     * I've planned to increase the functionality of this app by adding some intent activities
     * that will allow making a phone call, create a new contact,
     * show event place on map(and get directions how to get to the event place)
     * or even create the new appointment in user's calendar using data from app.
     * */

}
