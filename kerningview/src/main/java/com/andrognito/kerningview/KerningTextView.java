package com.andrognito.kerningview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * KerningTextView is a special {@link TextView} which allows adjusting
 * the spacing between characters in a piece of text AKA Kerning.
 * <p/>
 * You can use the {@code #setKerningFactor()} method to adjust the kerning programatically,
 * but the more common approach would be to use {@code ktv_spacing} attribute in xml.
 */
public class KerningTextView extends TextView {

    private final String TAG = getClass().getSimpleName();

    /**
     * Default kerning values which can be used for convenience
     */
    public class Kerning {
        public final static float NO_KERNING = 0;
        public final static float SMALL = 1;
        public final static float MEDIUM = 4;
        public final static float LARGE = 6;
    }

    private float mKerningFactor = Kerning.NO_KERNING;
    private CharSequence mOriginalText;

    public KerningTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public KerningTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public KerningTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, 0);
    }

    private void init(AttributeSet attrs, int defStyle) {

        TypedArray originalTypedArray = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.text});
        TypedArray currentTypedArray = getContext().obtainStyledAttributes(attrs, R.styleable.KerningViews, 0, defStyle);

        try {
            mKerningFactor = currentTypedArray.getFloat(R.styleable.KerningViews_kv_spacing, Kerning.NO_KERNING);
            mOriginalText = originalTypedArray.getText(0);
        } finally {
            originalTypedArray.recycle();
            currentTypedArray.recycle();
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.format("mKerningFactor: %s", mKerningFactor));
            Log.d(TAG, String.format("mOriginalText: %s", mOriginalText));
        }

        applyKerning();
    }

    /**
     * Programatically get the value of the {@code mKerningFactor}
     *
     * @return
     */
    public float getKerningFactor() {
        return mKerningFactor;
    }

    /**
     * Programatically set the value of the {@code mKerningFactor}
     *
     * @param kerningFactor
     */
    public void setKerningFactor(float kerningFactor) {
        this.mKerningFactor = kerningFactor;
        applyKerning();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        mOriginalText = text;
        applyKerning();
    }

    @Override
    public CharSequence getText() {
        return mOriginalText;
    }

    /**
     * The algorithm which applies the kerning to the {@link TextView}
     */
    private void applyKerning() {
        if (mOriginalText == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mOriginalText.length(); i++) {
            builder.append(mOriginalText.charAt(i));
            if (i + 1 < mOriginalText.length()) {
                builder.append("\u00A0");
            }
        }
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(new ScaleXSpan((mKerningFactor) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }
}
