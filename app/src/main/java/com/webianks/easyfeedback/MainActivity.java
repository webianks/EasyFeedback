package com.webianks.easyfeedback;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.webianks.easy_feedback.EasyFeedback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickFeedback(View view) {

        new EasyFeedback.Builder(this)
                .withEmail("username@gmail.com")
                .withSystemInfo()
                .build()
                .start();

    }
}
