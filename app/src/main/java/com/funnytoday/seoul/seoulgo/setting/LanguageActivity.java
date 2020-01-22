package com.funnytoday.seoul.seoulgo.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.util.Contact;

import java.util.Locale;


/**
 * Created by YunTaeSik on 2016-09-04.
 */
public class LanguageActivity extends Activity implements View.OnClickListener {
    private TextView ko_text, un_text, jp_text, ch_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.acitivity_language);
        setFinishOnTouchOutside(false);

        ko_text = (TextView) findViewById(R.id.ko_text);
        un_text = (TextView) findViewById(R.id.un_text);
        jp_text = (TextView) findViewById(R.id.jp_text);
        ch_text = (TextView) findViewById(R.id.ch_text);

        ko_text.setOnClickListener(this);
        un_text.setOnClickListener(this);
        jp_text.setOnClickListener(this);
        ch_text.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ko_text:
                Locale ko = Locale.KOREA;
                Configuration config = new Configuration();
                config.locale = ko;
                getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                finish();
                sendBroadcast(new Intent(Contact.LANGUAGE_CHANGE));
                break;
            case R.id.un_text:
                Locale en = Locale.US;
                Configuration config1 = new Configuration();
                config1.locale = en;
                getResources().updateConfiguration(config1, getResources().getDisplayMetrics());
                finish();
                sendBroadcast(new Intent(Contact.LANGUAGE_CHANGE));
                break;
            case R.id.jp_text:
                Locale jp = Locale.JAPAN;
                Configuration config2 = new Configuration();
                config2.locale = jp;
                getResources().updateConfiguration(config2, getResources().getDisplayMetrics());
                finish();
                sendBroadcast(new Intent(Contact.LANGUAGE_CHANGE));
                break;
            case R.id.ch_text:
                Locale zh = Locale.CHINESE;
                Configuration config3 = new Configuration();
                config3.locale = zh;
                getResources().updateConfiguration(config3, getResources().getDisplayMetrics());
                finish();
                sendBroadcast(new Intent(Contact.LANGUAGE_CHANGE));
                break;
        }
    }
}