package com.webianks.easy_feedback;

import android.content.Context;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class EasyFeedback {

    static Feedback feedback;

    public static Feedback init(Context context) {
        feedback = new Feedback(context);
        return feedback;
    }

}
