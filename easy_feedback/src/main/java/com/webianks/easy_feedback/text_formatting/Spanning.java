package com.webianks.easy_feedback.text_formatting;

import android.content.Context;
import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class Spanning {


    private Context context;
    private TextView info;
    private String appLable;

    public Spanning(Context context, TextView info, String appLabel) {
        this.context = context;
        this.info = info;
        this.appLable = appLabel;
    }

    public void colorPartOfText(boolean withInfo) {


        SpanHelper spanHelper = new SpanHelper(context);

        StyleableSpannableStringBuilder styleableSpannableStringBuilder = new StyleableSpannableStringBuilder();


        if (withInfo){

            styleableSpannableStringBuilder.append(spanHelper.your);
            styleableSpannableStringBuilder.appendWithStyle(spanHelper.clickableSpanSystemInfo, spanHelper.system_info);

            String will_be_sent = spanHelper.will_be_sent.toString();

            if (will_be_sent.contains("CompanyX"))
                will_be_sent = will_be_sent.replace("CompanyX", appLable);

            styleableSpannableStringBuilder.append(will_be_sent);
        }

        info.setText(styleableSpannableStringBuilder);
        info.setMovementMethod(LinkMovementMethod.getInstance());
        info.setHighlightColor(Color.TRANSPARENT);
        info.setEnabled(true);

    }
}
