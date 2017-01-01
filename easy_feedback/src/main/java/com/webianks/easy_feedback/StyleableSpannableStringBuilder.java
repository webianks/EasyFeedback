package com.webianks.easy_feedback;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;

/**
 * Created by R Ankit on 01-01-2017.
 */

public class StyleableSpannableStringBuilder extends SpannableStringBuilder {

    public StyleableSpannableStringBuilder appendWithStyle(CharacterStyle c, CharSequence text) {
        super.append(text);
        int startPos = length() - text.length();
        setSpan(c, startPos, length(), 0);
        return this;
    }

    public StyleableSpannableStringBuilder appendBold(CharSequence text) {
        return appendWithStyle(new StyleSpan(Typeface.BOLD), text);
    }
}