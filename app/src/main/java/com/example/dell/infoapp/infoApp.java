package com.example.dell.infoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;
import static android.R.id.message;

public class infoApp extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);


        /**
         * This code will create a phone call to given number
         */
        TextView makePhonecall = (TextView) findViewById(R.id.phone_number);
        //setting a clicklistener on that view
        makePhonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhoneCallConfirmation();

            }
        });

        /**
         * This code will open web browser on event's facebook fanpage
         */
        TextView openFanpage = (TextView) findViewById(R.id.fb_adress);
        //setting a clicklistener on that view
        openFanpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/wspolnotakoinonia"));
                startActivity(browserIntent);

                Toast.makeText(infoApp.this, getString(R.string.toast_browser), Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * This code will open mail application for create an email with specific adress email.
         */
        TextView sendEmail = (TextView) findViewById(R.id.mail_adress);
        //setting a clicklistener on that view
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use an intent to launch an email app.
                // Send the order summary in the email body.
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:contact.email[at]email.com")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.title, name));
                intent.putExtra(Intent.EXTRA_TEXT, message);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                Toast.makeText(infoApp.this, getString(R.string.toast_mail), Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * This code will open Google Maps on specific, predefined location (Event Place).
         */

        TextView showLocation = (TextView) findViewById(R.id.localisation);
        //setting a clicklistener on that view
        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "http://maps.google.com/maps?q=loc:" + 50.059047 + "," + 19.935457 + " (" + getString(R.string.event_place) + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);

                Toast.makeText(infoApp.this, getString(R.string.toast_maps), Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * This code will create an Event in users Calendar with predefined data.
         */

        TextView addEvent = (TextView) findViewById(R.id.event_time_desc);
        //setting a clickListener on that View
        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("rule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
                intent.putExtra("title", "A Test Event from android app");
                startActivity(intent);
                Toast.makeText(infoApp.this, getString(R.string.toast_event), Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * I've planned to increase the functionality of this app by adding some intent activities
         * that will allow making a phone call, create a new contact,
         * show event place on map(and get directions how to get to the event place)
         * or even create the new appointment in user's calendar using data from app.
         * */

    }

    private void showPhoneCallConfirmation() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_dialog_msg);
        builder.setPositiveButton(R.string.call, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Call" button, so it will create a phone call.
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0048 123 456 789"));
                startActivity(intent);
                Toast.makeText(infoApp.this, getString(R.string.toast_phonecall), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
