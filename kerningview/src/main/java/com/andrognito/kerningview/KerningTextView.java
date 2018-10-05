package com.andrognito.kerningview;

import static com.andrognito.kerningview.KerningTextView.Kerning.NO_KERNING;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
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
 * You can use the {@code #setKerningFactor()} method to adjust the kerning programmatically,
 * but the more common approach would be to use {@code ktv_spacing} attribute in xml.
 */
public class KerningTextView extends AppCompatTextView {

    private final String TAG = getClass().getSimpleName();

    private float kerningFactor = NO_KERNING;
    private CharSequence originalText;

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
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        TypedArray originalTypedArray = getContext().obtainStyledAttributes(attrs,
                new int[]{android.R.attr.text});
        TypedArray currentTypedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.KerningViews, 0, defStyle);

        try {
            kerningFactor = currentTypedArray.getFloat(R.styleable.KerningViews_kv_spacing,
                    NO_KERNING);
            CharSequence attributeText = originalTypedArray.getText(0);
            originalText = attributeText != null ? attributeText : "";
        } finally {
            originalTypedArray.recycle();
            currentTypedArray.recycle();
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.format("Kerning Factor: %s", kerningFactor));
            Log.d(TAG, String.format("Original Text: %s", originalText));
        }

        applyKerning();
    }

    /**
     * Programmatically get the value of the {@code kerningFactor}
     */
    public float getKerningFactor() {
        return kerningFactor;
    }

    /**
     * Programmatically set the value of the {@code kerningFactor}
     */
    public void setKerningFactor(float kerningFactor) {
        this.kerningFactor = kerningFactor;
        applyKerning();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        applyKerning();
    }

    @Override
    public CharSequence getText() {
        return originalText;
    }

    private void applyKerning() {
        if (originalText == null) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if (i + 1 < originalText.length()) {
                builder.append("\u00A0");
            }
        }

        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) {
            for (int i = 1; i < builder.toString().length(); i += 2) {
                finalText.setSpan(
                        new ScaleXSpan((kerningFactor) / 10),
                        i,
                        i + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, BufferType.SPANNABLE);
    }

    /**
     * Default kerning values which can be used for convenience
     */
    public class Kerning {
        public final static float NO_KERNING = 0;
        public final static float SMALL = 1;
        public final static float MEDIUM = 4;
        public final static float LARGE = 6;
    }
}
