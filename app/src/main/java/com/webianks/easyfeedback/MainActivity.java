package com.webianks.easyfeedback;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.webianks.easy_feedback.EasyFeedback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickFeedback(View view) {

        new EasyFeedback.Builder(this)
                .withEmail("webianks@gmail.com")
                .withSystemInfo()
                .build()
                .start();

    }
}
