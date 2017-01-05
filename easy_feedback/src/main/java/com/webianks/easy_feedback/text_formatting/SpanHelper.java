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


    public SpanHelper(Context context) {
        this.context = context;
    }

    final int colorBlue = 0xff0099cc;

    CharSequence your = "Your ";
    CharSequence system_info = "system info ";
    CharSequence will_be_sent = "will be sent to CompanyX. ";


    public ClickableSpan clickableSpanSystemInfo = new ClickableSpan() {
        @Override
        public void onClick(View widget) {

            new AlertDialog.Builder(context)
                    .setTitle("System Info")
                    .setMessage(DeviceInfo.getAllDeviceInfo(context, true))
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


}
