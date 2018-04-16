package com.andrognito.kerningviewsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andrognito.kerningview.KerningTextView;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        KerningTextView text3 = (KerningTextView) findViewById(R.id.text3);
        /**
         * Dynamically setting the kerning factor on a {@link KerningTextView}
         */
        if (text3 != null) {
            text3.setKerningFactor(3.5f);
        }
    }
}
