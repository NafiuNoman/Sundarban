package com.ets.bfd.visitor.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ets.bfd.visitor.R;

public class FontAwesome extends AppCompatTextView {
    private boolean isBrandingIcon, isSolidIcon, isRegularIcon;

    public FontAwesome(Context context) {
        super(context);
    }

    public FontAwesome(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontAwesome(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FontTextView,
                0, 0);
        isSolidIcon = a.getBoolean(R.styleable.FontTextView_solid_icon, false);
        isBrandingIcon = a.getBoolean(R.styleable.FontTextView_brand_icon, false);
        isRegularIcon = a.getBoolean(R.styleable.FontTextView_regular_icon, false);
        init();
    }

    private void init() {
        if (isBrandingIcon)
            setTypeface(FontCache.get(getContext(), "fonts/fa-brands-400.ttf"));
        else if (isSolidIcon)
            setTypeface(FontCache.get(getContext(), "fonts/fa-solid-900.ttf"));
        else if (isRegularIcon)
            setTypeface(FontCache.get(getContext(), "fonts/fontawesome-webfont.ttf"));
    }
}
