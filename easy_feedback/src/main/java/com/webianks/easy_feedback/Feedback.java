package com.webianks.easy_feedback;

import android.content.Context;
import android.content.Intent;

/**
 * Created by R Ankit on 03-01-2017.
 */
public class Feedback {

    Intent intent;
    Feedback feedback;
    Context context;

    public Feedback(Context context) {

        this.context = context;
        intent = new Intent(context, FeedbackActivity.class);
        feedback = this;
    }

    public Feedback setEmail(String email) {

        if (intent != null)
            intent.putExtra("email", email);

        return feedback;
    }

    public void start() {

        if (intent != null)
            context.startActivity(intent);

    }


}
