package com.webianks.easy_feedback.text_formatting;

import android.content.Context;
import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class Spanning {

    public static void colorPartOfText(Context context,TextView info, String appLable) {


        SpanHelper spanHelper = new SpanHelper(context);

        StyleableSpannableStringBuilder styleableSpannableStringBuilder = new StyleableSpannableStringBuilder();
        styleableSpannableStringBuilder.append(spanHelper.got_to_the);
        styleableSpannableStringBuilder.appendWithStyle(spanHelper.legalHelpSpan, spanHelper.legel_page);
        styleableSpannableStringBuilder.append(spanHelper.to_request);
        styleableSpannableStringBuilder.appendWithStyle(spanHelper.clickableSpanSystemInfo, spanHelper.system_info);


        String will_be_sent = spanHelper.will_be_sent.toString();

        if (will_be_sent.contains("CompanyX"))
            will_be_sent = will_be_sent.replace("CompanyX",appLable);

        styleableSpannableStringBuilder.append(will_be_sent);
        styleableSpannableStringBuilder.appendWithStyle(spanHelper.privacyPolicySpan, spanHelper.privacy);
        styleableSpannableStringBuilder.append(spanHelper.and);
        styleableSpannableStringBuilder.appendWithStyle(spanHelper.termsServiceSpan, spanHelper.terms_service);

        info.setText(styleableSpannableStringBuilder);
        info.setMovementMethod(LinkMovementMethod.getInstance());
        info.setHighlightColor(Color.TRANSPARENT);
        info.setEnabled(true);

    }
}
