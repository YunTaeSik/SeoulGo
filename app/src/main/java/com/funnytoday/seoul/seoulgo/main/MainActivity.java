package com.funnytoday.seoul.seoulgo.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.MainPagerAdapter;
import com.funnytoday.seoul.seoulgo.coach.CoachPage;
import com.funnytoday.seoul.seoulgo.dialog.finishDialog;
import com.funnytoday.seoul.seoulgo.service.SeoulService;
import com.funnytoday.seoul.seoulgo.setting.CouponActivity;
import com.funnytoday.seoul.seoulgo.setting.InfoActivity;
import com.funnytoday.seoul.seoulgo.setting.LanguageActivity;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.funnytoday.seoul.seoulgo.util.SharedPrefsUtils;

import java.util.Locale;

/**
 * Created by 박상돈 on 2016-08-15.
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

    public final static int FRAGMENT_PAGE1 = 0;
    public final static int FRAGMENT_PAGE2 = 1;
    public final static int FRAGMENT_PAGE3 = 2;
    private ViewPager mViewPager;
    private MainPagerAdapter mainPagerAdapter;
    private Button page1Btn, page2Btn, page3Btn;
    private ImageButton setting_btn1, setting_btn2, setting_btn3, setting_btn4, setting_btn5;
    private RelativeLayout shared_btn_layout;
    private RelativeLayout refresh_btn_layout;
    private LinearLayout main_underbar;

    private String guide_flag;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contact.COLLECTION_GO);
        intentFilter.addAction(Contact.LANGUAGE_CHANGE);
        intentFilter.addAction("realfinish");
        registerReceiver(broadcastReceiver, intentFilter);

        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage(); //언어 설정 가져오기
        startService(new Intent(this, SeoulService.class));
        shared_btn_layout = (RelativeLayout) findViewById(R.id.shared_btn_layout);
        refresh_btn_layout = (RelativeLayout) findViewById(R.id.refresh_btn_layout);
        main_underbar = (LinearLayout) findViewById(R.id.main_underbar);

        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), language);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mainPagerAdapter);
        mViewPager.setCurrentItem(FRAGMENT_PAGE2);
        mViewPager.setOffscreenPageLimit(3);

        page1Btn = (Button) findViewById(R.id.Page1Btn);
        page1Btn.setOnClickListener(this);
        page2Btn = (Button) findViewById(R.id.Page2Btn);
        page2Btn.setOnClickListener(this);
        page3Btn = (Button) findViewById(R.id.Page3Btn);
        page3Btn.setOnClickListener(this);

        setting_btn1 = (ImageButton) findViewById(R.id.setting_btn1);
        setting_btn1.setOnClickListener(this);
        setting_btn2 = (ImageButton) findViewById(R.id.setting_btn2);
        setting_btn2.setOnClickListener(this);
        setting_btn3 = (ImageButton) findViewById(R.id.setting_btn3);
        setting_btn3.setOnClickListener(this);
        setting_btn4 = (ImageButton) findViewById(R.id.setting_btn4);
        setting_btn4.setOnClickListener(this);
        setting_btn5 = (ImageButton) findViewById(R.id.setting_btn5);
        setting_btn5.setOnClickListener(this);

        mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                page1Btn.setSelected(false);
                page2Btn.setSelected(false);
                page3Btn.setSelected(false);

                switch (position) {
                    case 0:
                        shared_btn_layout.setVisibility(View.VISIBLE);
                        refresh_btn_layout.setVisibility(View.VISIBLE);
                        page1Btn.setSelected(true);
                        main_underbar.setBackgroundColor(Color.parseColor("#555555"));
                        break;
                    case 1:
                        shared_btn_layout.setVisibility(View.GONE);
                        refresh_btn_layout.setVisibility(View.GONE);
                        page2Btn.setSelected(true);
                        main_underbar.setBackgroundColor(Color.parseColor("#555555"));
                        break;
                    case 2:
                        shared_btn_layout.setVisibility(View.GONE);
                        refresh_btn_layout.setVisibility(View.GONE);
                        page3Btn.setSelected(true);
                        main_underbar.setBackgroundColor(Color.parseColor("#555555"));
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });

        page2Btn.setSelected(true);
        if (language.equals("ko")) {
            CoachCheck();
        }
    }

    private void CoachCheck() {
        guide_flag = SharedPrefsUtils.getStringPreference(getApplicationContext(), "GUIDE");
        if (guide_flag == null) {
            SharedPrefsUtils.setStringPreference(this, "GUIDE", "0");
        }
        try {
            if (guide_flag.equals("0")) { //켜지고
                Intent intent = new Intent(this, CoachPage.class);
                startActivity(intent);
            }
        } catch (NullPointerException e) {
            Intent intent = new Intent(this, CoachPage.class);
            startActivity(intent);
        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.Page1Btn:
                mViewPager.setCurrentItem(FRAGMENT_PAGE1);
                break;
            case R.id.Page2Btn:
                mViewPager.setCurrentItem(FRAGMENT_PAGE2);
                break;
            case R.id.Page3Btn:
                mViewPager.setCurrentItem(FRAGMENT_PAGE3);
                break;
            case R.id.setting_btn1:
                Intent intent1 = new Intent(this, InfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.setting_btn2:
                Intent intent2 = new Intent(this, CouponActivity.class);
                startActivity(intent2);
                break;
            case R.id.setting_btn3:
                Intent intent3 = new Intent(this, LanguageActivity.class);
                startActivity(intent3);
                break;
            case R.id.setting_btn4:
                permissionCheck();
                break;
            case R.id.setting_btn5:
            case R.id.refresh_btn_layout:
                Intent intent = new Intent(Contact.COLLECTION_FINISH);
                sendBroadcast(intent);
                break;
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Contact.LANGUAGE_CHANGE)) {
                mainPagerAdapter.notifyDataSetChanged();
                recreate();
            } else if (intent.getAction().equals(Contact.COLLECTION_GO)) {
                mViewPager.setCurrentItem(FRAGMENT_PAGE1);
            } else if (intent.getAction().equals("realfinish")) {
                finish();
            }
        }
    };

    private void permissionCheck() {
        int permissionCheck_STORAGE = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck_STORAGE == PackageManager.PERMISSION_DENIED) { //권한 없음
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Intent intent = new Intent(Contact.SHARED_CLICK);
            sendBroadcast(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Contact.SHARED_CLICK);
                        sendBroadcast(intent);
                    } else {
                        Toast.makeText(this, getString(R.string.permit_text), Toast.LENGTH_SHORT).show();
                    }
                }
                return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, finishDialog.class);
        startActivity(intent);
        // super.onBackPressed();
    }
}
