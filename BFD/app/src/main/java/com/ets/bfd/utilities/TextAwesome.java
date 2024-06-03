package com.ets.bfd.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.LruCache;
import android.widget.TextView;

/**
 * Created by Shipon on 9/6/2016.
 */
public class TextAwesome extends TextView {

    private final static String NAME = "FONTAWESOME";
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);

    public TextAwesome(Context context) {
        super(context);
        init();

    }

    public TextAwesome(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {

        Typeface typeface = sTypefaceCache.get(NAME);

        if (typeface == null) {

            typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/fontawesome-webfont.ttf");
            sTypefaceCache.put(NAME, typeface);

        }

        setTypeface(typeface);

    }

}
