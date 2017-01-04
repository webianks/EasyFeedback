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
    private String legalHelp;
    private String terms;
    private String policy;

    public EasyFeedback(Builder builder) {

        this.emailId = builder.emailId;
        this.context = builder.context;
        this.withSystemInfo = builder.withSystemInfo;
        this.policy = builder.policy;
        this.terms = builder.terms;
        this.legalHelp = builder.legalHelp;

    }

    public static class Builder {

        private Context context;
        private String emailId;
        private boolean withSystemInfo;
        private String legalHelp;
        private String terms;
        private String policy;

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

        public Builder legalHelp(String legalHelp) {
            this.legalHelp = legalHelp;
            return this;
        }

        public Builder privacyPolicy(String policy) {
            this.policy = policy;
            return this;
        }

        public Builder termsOfService(String terms) {
            this.terms = terms;
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
        intent.putExtra("policy", policy);
        intent.putExtra("legal", legalHelp);
        intent.putExtra("terms", terms);
        context.startActivity(intent);


    }

}
