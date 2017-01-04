package com.webianks.easyfeedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.webianks.easy_feedback.EasyFeedback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.ic_feedback:

                EasyFeedback.init(this)
                        .setEmail("webianks@gmail.com") // developer's Email-Id
                        // setAccentColor [optional]
                        // provide LegalHelpUrl [optional]
                        // provide privacyPolicy [optional]
                        // provide termsOfService [optional]
                        .start();

                break;
        }

        return true;
    }
}
