package com.malmstein.uglyforms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewAnimator;

public class DemoActivity extends Activity implements View.OnClickListener {

    private ViewAnimator formAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ugly_form);

        findViews();
    }

    private void findViews(){
        formAnimator = (ViewAnimator) findViewById(R.id.form_animator);
        findViewById(R.id.welcome_login).setOnClickListener(this);
        findViewById(R.id.welcome_register).setOnClickListener(this);
        findViewById(R.id.register_cancel).setOnClickListener(this);
        findViewById(R.id.login_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.welcome_login:
                formAnimator.showNext();
                break;
            case R.id.welcome_register:
                formAnimator.showPrevious();
                break;
            case R.id.register_cancel:
                formAnimator.showNext();
                break;
            case R.id.login_cancel:
                formAnimator.showPrevious();
                break;
            default:
                new Exception("This view does not exist");
        }
    }
}
