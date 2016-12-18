package com.webianks.easy_feedback;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class FeedbackActivity extends AppCompatActivity {

    private Spinner accountSpinner;
    private String TAG = FeedbackActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        accountSpinner = (Spinner) findViewById(R.id.account_spinner);
        fillSpinner();
    }

    private void fillSpinner() {

        List<String> possibleEmails = new LinkedList<>();
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");

        Log.d(TAG," "+accounts.length);

        for (Account account : accounts) {

            if (emailPattern.matcher(account.name).matches()) {

                String possibleEmail = account.name;
                possibleEmails.add(possibleEmail);
                Log.d(TAG,possibleEmail);
            }

        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.activity_list_item,possibleEmails);
        accountSpinner.setAdapter(spinnerAdapter);
    }
}
