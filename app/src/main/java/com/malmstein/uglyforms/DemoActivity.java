package com.malmstein.uglyforms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

public class DemoActivity extends Activity implements View.OnClickListener {

    private Animation slideInRight, slideOutLeft, slideInLeft, slideOutRight;
    private ViewAnimator formAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ugly_form);

        findViews();
        initAnimator();
    }

    private void findViews(){
        formAnimator = (ViewAnimator) findViewById(R.id.form_animator);
        findViewById(R.id.welcome_login).setOnClickListener(this);
        findViewById(R.id.welcome_register).setOnClickListener(this);
        findViewById(R.id.register_cancel).setOnClickListener(this);
        findViewById(R.id.login_cancel).setOnClickListener(this);
    }

    private void initAnimator(){
        initSwitcherAnimations();
        initAnimatorPosition();
    }

    private void initSwitcherAnimations() {
        slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slideOutRight = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
    }

    private void initAnimatorPosition(){
        formAnimator.setDisplayedChild(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welcome_login:
                animateNext();
                break;
            case R.id.welcome_register:
                animateLeft();
                break;
            case R.id.register_cancel:
                animateNext();
                break;
            case R.id.login_cancel:
                animateLeft();
                break;
            default:
                new Exception("This view does not exist");
        }
    }

    private void animateNext(){
        slideInRight();
        formAnimator.showNext();
    }

    private void animateLeft() {
        slideInLeft();
        formAnimator.showPrevious();
    }

    private void slideInLeft() {
        formAnimator.setInAnimation(slideInLeft);
        formAnimator.setOutAnimation(slideOutRight);
    }

    private void slideInRight() {
        formAnimator.setInAnimation(slideInRight);
        formAnimator.setOutAnimation(slideOutLeft);
    }
}
