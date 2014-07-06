package com.malmstein.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        initialChild = a.getInt(R.styleable.MultipleViewSwitcher_initialPage, 0);

        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        displayInitialViewIfNecessary();
    }

    private void displayInitialViewIfNecessary() {
        if (initialChild > 0) {
            setDisplayedChild(initialChild);
        }
    }

    /**
     * Animates the view to the next children using the animations specified
     *
     * @param context              The application's environment.
     * @param inAnimationResource  The resource id of the inAnimation.
     * @param outAnimationResource The resource id of the outAnimation.
     */
    public void animateNext(Context context, int inAnimationResource, int outAnimationResource) {
        animateNext(AnimationUtils.loadAnimation(context, inAnimationResource),
                AnimationUtils.loadAnimation(context, outAnimationResource));
    }

    /**
     * Animates the view to the next children using the animations specified
     *
     * @param inAnimation The inAnimation
     * @param outAnimation The outAnimation
     */
    public void animateNext(Animation inAnimation, Animation outAnimation) {
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);
        showNext();
    }

    /**
     * Animates the view to the previous children using the animations specified
     *
     * @param context              The application's environment.
     * @param inAnimationResource  The resource id of the inAnimation.
     * @param outAnimationResource The resource id of the outAnimation.
     */
    public void animatePrevious(Context context, int inAnimationResource, int outAnimationResource) {
        animatePrevious(AnimationUtils.loadAnimation(context, inAnimationResource),
                AnimationUtils.loadAnimation(context, outAnimationResource));
    }

    /**
     * Animates the view to the previous children using the animations specified
     *
     * @param inAnimation The inAnimation
     * @param outAnimation The outAnimation
     */
    public void animatePrevious(Animation inAnimation, Animation outAnimation) {
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);
        showPrevious();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.position = getDisplayedChild();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        setDisplayedChild(ss.position);
    }

    /**
     * This is the persistent state that is saved by MulipleViewSwitcher.  Only needed
     * if you are creating a sublass of ViewPager that must save its own
     * state, in which case it should implement a subclass of this which
     * contains that state.
     */
    public static class SavedState extends AbsSavedState {
        int position;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(position);
        }

        @Override
        public String toString() {
            return "MulipleViewSwitcher.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " position=" + position + "}";
        }

        public static final Creator CREATOR = new Creator() {
            @Override
            public Object createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public Object[] newArray(int size) {
                return new SavedState[size];
            }
        };

        SavedState(Parcel in) {
            super(in);
            position = in.readInt();
        }
    }

}
