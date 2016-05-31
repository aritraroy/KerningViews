package com.andrognito.kerningview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * KerningButton is a special {@link Button} which allows
 * to adjust the character spacing in the text of the button
 */
public class KerningButton extends KerningTextView {

    public KerningButton(Context context) {
        super(context);
    }

    public KerningButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KerningButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }
}
