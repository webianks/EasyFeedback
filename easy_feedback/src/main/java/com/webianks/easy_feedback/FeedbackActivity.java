package com.webianks.easy_feedback;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webianks.easy_feedback.components.DeviceInfo;
import com.webianks.easy_feedback.components.SystemLog;
import com.webianks.easy_feedback.text_formatting.Spanning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static com.webianks.easy_feedback.components.DeviceInfo.Device.DEVICE_LANGUAGE;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner accountSpinner;
    private String TAG = FeedbackActivity.class.getSimpleName();
    private int REQUEST_APP_SETTINGS = 321;
    private TextView info;
    private Button submitSuggestion;
    private EditText editText;
    private String emailId;
    private final int REQUEST_PERMISSIONS = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        init();
        fillSpinnerWrapper();
        Spanning.colorPartOfText(info);


        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_SYSTEM_VERSION));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_VERSION));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DEVICE_LANGUAGE));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_TIME_ZONE));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_TOTAL_MEMORY));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_USED_MEMORY));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_FREE_MEMORY));
        Log.d("webi",DeviceInfo.getDeviceInfo(this,DeviceInfo.Device.DEVICE_TYPE));
        Log.d("webi",DeviceInfo.getDataType(this));


    }

    private void init() {
        accountSpinner = (Spinner) findViewById(R.id.account_spinner);
        editText = (EditText) findViewById(R.id.editText);
        info = (TextView) findViewById(R.id.info_legal);
        submitSuggestion = (Button) findViewById(R.id.submitSuggestion);
        submitSuggestion.setOnClickListener(this);

        emailId = getIntent().getStringExtra("email");

    }


    private void fillSpinnerWrapper() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            int hasWriteContactsPermission = checkSelfPermission(android.Manifest.permission.GET_ACCOUNTS);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{android.Manifest.permission.GET_ACCOUNTS},
                        REQUEST_PERMISSIONS);
                return;

            } else
                //already granted
                fillSpinner();


        } else {
            //normal process
            fillSpinner();
        }

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

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, finalEmails);
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case REQUEST_PERMISSIONS:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    fillSpinner();

                } else {
                    // Permission Denied
                    showMessageOKCancel("You need to allow access to Accounts to access your email id.",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    goToSettings();

                                }
                            });

                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        }
    }


    private void goToSettings() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(myAppSettings, REQUEST_APP_SETTINGS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_APP_SETTINGS) {

            if (hasPermissions(Manifest.permission.GET_ACCOUNTS)) {

                //Toast.makeText(this, "All permissions granted!", Toast.LENGTH_SHORT).show();
                fillSpinner();

            } else {

                showMessageOKCancel("You need to allow access to Accounts to access your email id.",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                goToSettings();

                            }
                        });

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean hasPermissions(@NonNull String... permissions) {
        for (String permission : permissions)
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permission))
                return false;
        return true;
    }


    public void sendEmail(String body) {

        StringBuilder finalBody = new StringBuilder(body);

        finalBody.append(SystemLog.extractLogToString());

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, finalBody.toString());
        emailIntent.setData(Uri.parse("mailto: " + emailId));
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_feedback_two)));

    }


    @Override
    public void onClick(View view) {

        String suggestion = editText.getText().toString();

        if (suggestion.trim().length() > 0)
            sendEmail(suggestion);
        else
            Toast.makeText(this, getString(R.string.please_write), Toast.LENGTH_LONG).show();

    }


}
