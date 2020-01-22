package com.funnytoday.seoul.seoulgo.intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.AnalyticsApplication;
import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.main.MainActivity;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;


/**
 * Created by YunTaeSik on 2016-08-15.
 */
public class IntroActivity extends Activity {

    private Handler h;//핸들러 선언
    private Animation alphaAni;
    private Animation alphaAni_two;
    private Animation alphaAni_three;
    private Animation alphaAni_four;
    private Animation hide_alpha_one;
    private Animation hide_alpha_two;
    private Animation hide_alpha_three;
    private Animation hide_alpha_four;
    private TextView[] loading_circle = new TextView[4];
    private TextView hide_view_one, hide_view_two, hide_view_three, hide_view_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //인트로화면이므로 타이틀바를 없앤다
        setContentView(R.layout.activity_intro);

        AnalyticsApplication.tracker().setScreenName("IntroActivity");
        AnalyticsApplication.tracker().send(new HitBuilders.EventBuilder().build());

        loading_circle[0] = (TextView) findViewById(R.id.loading_circle_one);
        loading_circle[1] = (TextView) findViewById(R.id.loading_circle_two);
        loading_circle[2] = (TextView) findViewById(R.id.loading_circle_three);
        loading_circle[3] = (TextView) findViewById(R.id.loading_circle_four);

        hide_view_one = (TextView) findViewById(R.id.hide_view_one);
        hide_view_two = (TextView) findViewById(R.id.hide_view_two);
        hide_view_three = (TextView) findViewById(R.id.hide_view_three);
        hide_view_four = (TextView) findViewById(R.id.hide_view_four);

        alphaAni = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        alphaAni_two = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_two);
        alphaAni_three = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_three);
        alphaAni_four = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_four);

        hide_alpha_one = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hide_alpha_one);
        hide_alpha_two = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hide_alpha_two);
        hide_alpha_three = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hide_alpha_three);
        hide_alpha_four = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hide_alpha_four);

        loading_circle[0].startAnimation(alphaAni);
        loading_circle[1].startAnimation(alphaAni_two);
        loading_circle[2].startAnimation(alphaAni_three);
        loading_circle[3].startAnimation(alphaAni_four);

        hide_view_one.startAnimation(hide_alpha_one);
        hide_view_two.startAnimation(hide_alpha_two);
        hide_view_three.startAnimation(hide_alpha_three);
        hide_view_four.startAnimation(hide_alpha_four);
        // logo_image_intro.startAnimation(alphaAni);


        h = new Handler(); //딜래이를 주기 위해 핸들러 생성
        h.postDelayed(mrun, 2000); // 딜레이 ( 런어블 객체는 mrun, 시간 2초)

    }

    Runnable mrun = new Runnable() {
        @Override
        public void run() {

            Intent i = new Intent(IntroActivity.this, MainActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
            startActivity(i);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        GoogleAnalytics.getInstance(this).reportActivityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (requestCode == 1) {
            if (resultCode == 1) {
                Intent i = new Intent(IntroActivity.this, MainActivity.class); //인텐트 생성(현 액티비티, 새로 실행할 액티비티)
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        h.removeCallbacks(mrun);
    }

}
