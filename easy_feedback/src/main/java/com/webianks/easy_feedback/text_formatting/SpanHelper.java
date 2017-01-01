package com.webianks.easy_feedback.text_formatting;


import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class SpanHelper {


    final static int colorBlue = 0xff0099cc;

    public static CharSequence got_to_the = "Go to the";
    public static CharSequence legel_page = " Legal Help Page";
    public static CharSequence to_request = " to request content changes for legal reasons. Your ";
    public static CharSequence system_info = "system info ";
    public static CharSequence will_be_sent = "will be sent to CompanyX. See ";
    public static CharSequence privacy = "privacy policy ";
    public static CharSequence and = "and ";
    public static CharSequence terms_service = "terms of service.";



    public static ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            //your code at here.
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(colorBlue);
        }
    };

    public static ClickableSpan clickableSpan2 = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            //your code at here.
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(colorBlue);
        }
    };


    public static ClickableSpan clickableSpan3 = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            //your code at here.
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(colorBlue);
        }
    };


    public static ClickableSpan clickableSpan4 = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            //your code at here.
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(colorBlue);
        }
    };

}
