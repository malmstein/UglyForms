package com.malmstein.uglyforms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.malmstein.widgets.MultipleViewSwitcher;

public class DemoActivity extends Activity implements View.OnClickListener {

    private static final String KEY_DISPLAYED_CHILD = "KEY_DISPLAYED_CHILD";
    private Animation slideInRight, slideOutLeft, slideInLeft, slideOutRight;
    private MultipleViewSwitcher formAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ugly_form);

        findViews();
        initAnimator();
    }

    private void findViews() {
        formAnimator = (MultipleViewSwitcher) findViewById(R.id.form_multiple_view_switcher);
        findViewById(R.id.welcome_login).setOnClickListener(this);
        findViewById(R.id.welcome_register).setOnClickListener(this);
        findViewById(R.id.register_cancel).setOnClickListener(this);
        findViewById(R.id.login_cancel).setOnClickListener(this);
    }

    private void initAnimator() {
        initSwitcherAnimations();
    }

    private void initSwitcherAnimations() {
        slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slideOutRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_login:
                formAnimator.animateNext(slideInRight, slideOutLeft);
                break;
            case R.id.welcome_register:
                formAnimator.animatePrevious(slideInLeft, slideOutRight);
                break;
            case R.id.register_cancel:
                formAnimator.animateNext(slideInRight, slideOutLeft);
                break;
            case R.id.login_cancel:
                formAnimator.animatePrevious(slideInLeft, slideOutRight);
                break;
            default:
                new Exception("This view does not exist");
        }
    }

}
