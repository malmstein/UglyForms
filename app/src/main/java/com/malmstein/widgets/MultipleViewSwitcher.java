package com.malmstein.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

import com.malmstein.uglyforms.R;

public class MultipleViewSwitcher extends ViewAnimator {

    private int initialChild;

    public MultipleViewSwitcher(Context context) {
        this(context, null);
    }

    public MultipleViewSwitcher(Context context, AttributeSet attrs) {

        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultipleViewSwitcher);
        initialChild = a.getInt(R.styleable.MultipleViewSwitcher_initialChild, 0);

        a.recycle();
    }

    private void displayInitialViewIfNecessary() {
        setDisplayedChild(initialChild);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        displayInitialViewIfNecessary();
    }
}
