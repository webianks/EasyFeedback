package com.webianks.easy_feedback.text_formatting;

import android.graphics.Color;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class Spanning {

    public static void colorPartOfText(TextView info, String appLable) {

        StyleableSpannableStringBuilder styleableSpannableStringBuilder = new StyleableSpannableStringBuilder();
        styleableSpannableStringBuilder.append(SpanHelper.got_to_the);
        styleableSpannableStringBuilder.appendWithStyle(SpanHelper.clickableSpan, SpanHelper.legel_page);
        styleableSpannableStringBuilder.append(SpanHelper.to_request);
        styleableSpannableStringBuilder.appendWithStyle(SpanHelper.clickableSpan2, SpanHelper.system_info);


        String will_be_sent = SpanHelper.will_be_sent.toString();

        if (will_be_sent.contains("CompanyX"))
            will_be_sent = will_be_sent.replace("CompanyX",appLable);

        styleableSpannableStringBuilder.append(will_be_sent);
        styleableSpannableStringBuilder.appendWithStyle(SpanHelper.clickableSpan3, SpanHelper.privacy);
        styleableSpannableStringBuilder.append(SpanHelper.and);
        styleableSpannableStringBuilder.appendWithStyle(SpanHelper.clickableSpan4, SpanHelper.terms_service);

        info.setText(styleableSpannableStringBuilder);
        info.setMovementMethod(LinkMovementMethod.getInstance());
        info.setHighlightColor(Color.TRANSPARENT);
        info.setEnabled(true);

    }
}
