package com.webianks.easy_feedback;

import android.content.Context;
import android.content.Intent;

/**
 * Created by R Ankit on 28-10-2016.
 */

public class EasyFeedback {

    private Context context;
    private String emailId;
    private boolean withSystemInfo;


    public EasyFeedback(Builder builder) {

        this.emailId = builder.emailId;
        this.context = builder.context;
        this.withSystemInfo = builder.withSystemInfo;

    }

    public static class Builder {

        private Context context;
        private String emailId;
        private boolean withSystemInfo;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder withEmail(String email) {
            this.emailId = email;
            return this;
        }

        public Builder withSystemInfo() {
            withSystemInfo = true;
            return this;
        }


        public EasyFeedback build() {
            return new EasyFeedback(this);
        }

    }

    public void start() {

        Intent intent = new Intent(context, FeedbackActivity.class);
        intent.putExtra("email", emailId);
        intent.putExtra("with_info", withSystemInfo);
        context.startActivity(intent);

    }

}
