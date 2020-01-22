package com.funnytoday.seoul.seoulgo.coach;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.CoachPagerAdapter;
import com.funnytoday.seoul.seoulgo.util.SharedPrefsUtils;

/**
 * Created by Note on 2016-10-11.
 */
public class CoachPage extends AppCompatActivity implements View.OnClickListener {
    private Button Btn_NoView;
    private Button Btn_close;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coachpage);
        Btn_NoView = (Button) findViewById(R.id.buttonNoview);
        Btn_close = (Button) findViewById(R.id.buttonclose);
        Btn_NoView.setOnClickListener(this);
        Btn_close.setOnClickListener(this);
        pager = (ViewPager) findViewById(R.id.pager);
        CoachPagerAdapter adapter = new CoachPagerAdapter(getLayoutInflater(), this);
        pager.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonNoview:
                SharedPrefsUtils.setStringPreference(getApplicationContext(), "GUIDE", "1");//저장
                finish();
                break;
            case R.id.buttonclose:
                finish();
                break;

        }
    }
}
