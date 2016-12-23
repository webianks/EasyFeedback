package com.webianks.easy_feedback;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class FeedbackActivity extends AppCompatActivity {

    private Spinner accountSpinner;
    private String TAG = FeedbackActivity.class.getSimpleName();
    private final int MY_PERMISSIONS_REQUEST = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        accountSpinner = (Spinner) findViewById(R.id.account_spinner);
        fillSpinnerWrapper();
    }


    private void fillSpinnerWrapper() {

        int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.GET_ACCOUNTS);

        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS)) {
                showMessageOKCancel("You need to allow access to Contacts",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(FeedbackActivity.this,
                                        new String[] {Manifest.permission.GET_ACCOUNTS},
                                        MY_PERMISSIONS_REQUEST);
                            }
                        });
                return;
            }
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.GET_ACCOUNTS},
                    MY_PERMISSIONS_REQUEST);
            return;
        }
        fillSpinner();
    }

    private void fillSpinner() {

        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(this).getAccounts();
        Log.d(TAG, " " + accounts.length);
        ArrayList<String> emails = new ArrayList<String>();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                emails.add(account.name);
            }
        }

        Set<String> noDups = new HashSet<String>();
        noDups.addAll(emails);
        ArrayList<String> finalEmails = new ArrayList<String>(noDups);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, finalEmails);
        accountSpinner.setAdapter(dataAdapter);

    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    fillSpinner();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
