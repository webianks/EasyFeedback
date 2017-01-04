package com.webianks.easy_feedback.text_formatting;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.webianks.easy_feedback.components.DeviceInfo;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class SpanHelper {

    private Context context;

    public SpanHelper(Context context){
        this.context = context;
    }

    final int colorBlue = 0xff0099cc;

    public  CharSequence got_to_the = "Go to the";
    public  CharSequence legel_page = " Legal Help Page";
    public  CharSequence to_request = " to request content changes for legal reasons. Your ";
    public  CharSequence system_info = "system info ";
    public  CharSequence will_be_sent = "will be sent to CompanyX. See ";
    public  CharSequence privacy = "privacy policy ";
    public  CharSequence and = "and ";
    public  CharSequence terms_service = "terms of service.";



    public  ClickableSpan clickableSpan = new ClickableSpan() {
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

    public  ClickableSpan clickableSpanSystemInfo = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            //your code at here.

            new AlertDialog.Builder(context)
                    .setTitle("System Info")
                    .setMessage(DeviceInfo.getAllDeviceInfo(context,true))
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           dialog.dismiss();
                    }
                    })
                    .show();

        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
            ds.setColor(colorBlue);
        }
    };


    public  ClickableSpan clickableSpan3 = new ClickableSpan() {
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


    public  ClickableSpan clickableSpan4 = new ClickableSpan() {
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
