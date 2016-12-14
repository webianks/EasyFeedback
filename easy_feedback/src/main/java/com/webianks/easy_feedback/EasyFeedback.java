package com.webianks.easy_feedback;

import android.content.Context;
import android.content.Intent;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class EasyFeedback {

    public static void begin(Context context) {
        context.startActivity(new Intent(context, FeedbackActivity.class));
    }
}
