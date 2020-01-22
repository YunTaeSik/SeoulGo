package com.funnytoday.seoul.seoulgo.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.animation.HomeActivitySetting;


/**
 * Created by 박상돈 on 2016-09-06.
 */
public class InfoActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout info_usemethod1, info_usemethod2, info_caution1, info_caution2, info_mail1, info_mail2;
    private LinearLayout info_mail_explain, info_usemethod_explain, info_caution_explain;
    private ImageButton info_usemethod3, info_caution3, info_mail3;
    private String menuFlag1 = "on", menuFlag2 = "on", menuFlag3 = "on";
    private ScrollView info_scroll;

    private LinearLayout park, pig, yun, lee;
    private Button park1, pig1, yun1, lee1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_page);

        info_usemethod1 = (LinearLayout) findViewById(R.id.info_usemethod1);
        info_usemethod2 = (LinearLayout) findViewById(R.id.info_usemethod2);
        info_caution1 = (LinearLayout) findViewById(R.id.info_caution1);
        info_caution2 = (LinearLayout) findViewById(R.id.info_caution2);
        info_mail1 = (LinearLayout) findViewById(R.id.info_mail1);
        info_mail2 = (LinearLayout) findViewById(R.id.info_mail2);

        info_scroll = (ScrollView) findViewById(R.id.info_scroll);

        info_usemethod_explain = (LinearLayout) findViewById(R.id.info_usemethod_explain);
        info_caution_explain = (LinearLayout) findViewById(R.id.info_caution_explain);
        info_mail_explain = (LinearLayout) findViewById(R.id.info_mail_explain);

        info_usemethod3 = (ImageButton) findViewById(R.id.info_usemethod3);
        info_caution3 = (ImageButton) findViewById(R.id.info_caution3);
        info_mail3 = (ImageButton) findViewById(R.id.info_mail3);

        info_usemethod1.setOnClickListener(this);
        info_usemethod2.setOnClickListener(this);
        info_usemethod3.setOnClickListener(this);
        info_caution1.setOnClickListener(this);
        info_caution2.setOnClickListener(this);
        info_caution3.setOnClickListener(this);
        info_mail1.setOnClickListener(this);
        info_mail2.setOnClickListener(this);
        info_mail3.setOnClickListener(this);

        park = (LinearLayout) findViewById(R.id.park);
        lee = (LinearLayout) findViewById(R.id.lee);
        pig = (LinearLayout) findViewById(R.id.pig);
        yun = (LinearLayout) findViewById(R.id.yun);

        park.setOnClickListener(this);
        lee.setOnClickListener(this);
        pig.setOnClickListener(this);
        yun.setOnClickListener(this);


        park1 = (Button) findViewById(R.id.park1);
        lee1 = (Button) findViewById(R.id.lee1);
        pig1 = (Button) findViewById(R.id.pig1);
        yun1 = (Button) findViewById(R.id.yun1);

        park1.setOnClickListener(this);
        lee1.setOnClickListener(this);
        pig1.setOnClickListener(this);
        yun1.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_usemethod1:
            case R.id.info_usemethod2:
            case R.id.info_usemethod3:
                if (menuFlag1.equals("on")) {

                    HomeActivitySetting.HelpMenuOn(info_usemethod_explain, menuFlag1);
                    menuFlag1 = "off";

                } else if (menuFlag1.equals("off")) {

                    HomeActivitySetting.HelpMenuOff(info_usemethod_explain, menuFlag1);
                    menuFlag1 = "on";
                }
                break;
            case R.id.info_caution1:
            case R.id.info_caution2:
            case R.id.info_caution3:
                if (menuFlag2.equals("on")) {

                    HomeActivitySetting.HelpMenuOn(info_caution_explain, menuFlag1);
                    menuFlag2 = "off";

                } else if (menuFlag2.equals("off")) {

                    HomeActivitySetting.HelpMenuOff(info_caution_explain, menuFlag1);
                    menuFlag2 = "on";
                }
                break;
            case R.id.info_mail1:
            case R.id.info_mail2:
            case R.id.info_mail3:
                info_scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        info_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                if (menuFlag3.equals("on")) {

                    HomeActivitySetting.HelpMenuOn(info_mail_explain, menuFlag1);
                    menuFlag3 = "off";

                } else if (menuFlag3.equals("off")) {

                    HomeActivitySetting.HelpMenuOff(info_mail_explain, menuFlag1);
                    menuFlag3 = "on";
                }
                break;

            case R.id.park:
            case R.id.park1:
                Uri uri = Uri.parse("mailto:psdkei@naver.com");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(it);
                break;
            case R.id.yun:
            case R.id.yun1:
                Uri uri1 = Uri.parse("mailto:sky877kr@gmail.com");
                Intent it1 = new Intent(Intent.ACTION_SENDTO, uri1);
                startActivity(it1);
                break;
            case R.id.lee:
            case R.id.lee1:
                Uri uri2 = Uri.parse("mailto:steven15@naver.com");
                Intent it2 = new Intent(Intent.ACTION_SENDTO, uri2);
                startActivity(it2);
                break;
            case R.id.pig:
            case R.id.pig1:
                Uri uri3 = Uri.parse("mailto:chl8263@naver.com");
                Intent it3 = new Intent(Intent.ACTION_SENDTO, uri3);
                startActivity(it3);
                break;

        }
    }
}