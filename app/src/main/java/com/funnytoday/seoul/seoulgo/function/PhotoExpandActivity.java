package com.funnytoday.seoul.seoulgo.function;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by YunTaeSik on 2016-10-28.
 */
public class PhotoExpandActivity extends Activity implements View.OnClickListener {
    private TextView close_text;
    private ImageView photo_expand_image;
    private TextView photo_expand_contend;
    private String tag;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_photoexpand);
        setFinishOnTouchOutside(false);
        close_text = (TextView) findViewById(R.id.close_text);
        photo_expand_contend = (TextView) findViewById(R.id.photo_expand_contend);
        photo_expand_image = (ImageView) findViewById(R.id.photo_expand_image);
        tag = getIntent().getStringExtra("tag");
        position = getIntent().getIntExtra("position", 0);

        if (tag.equals("경복궁")) {
            Picasso.with(getApplicationContext()).load(Contact.Gyeongbokgung_PhotoZone[position]).fit().into(photo_expand_image);
            photo_expand_contend.setText(getResources().getStringArray(R.array.gyeongbokgung_photo_content)[position]);
            photo_expand_contend.setBackgroundColor(getResources().getColor(R.color.theme));
        } else if (tag.equals("창경궁")) {
            Picasso.with(getApplicationContext()).load(Contact.Changgyeonggung_PhotoZone[position]).fit().into(photo_expand_image);
            photo_expand_contend.setText(getResources().getStringArray(R.array.changgyeonggung_photo_content)[position]);
            photo_expand_contend.setBackgroundColor(getResources().getColor(R.color.toplayout_one_color));
        } else if (tag.equals("덕수궁")) {
            Picasso.with(getApplicationContext()).load(Contact.Deoksugung_PhotoZone[position]).fit().into(photo_expand_image);
            photo_expand_contend.setText(getResources().getStringArray(R.array.deoksugung_photo_content)[position]);
            photo_expand_contend.setBackgroundColor(getResources().getColor(R.color.toplayout_two_color));
        } else if (tag.equals("창덕궁")) {
            Picasso.with(getApplicationContext()).load(Contact.Changdeokgung_PhotoZone[position]).fit().into(photo_expand_image);
            photo_expand_contend.setText(getResources().getStringArray(R.array.changdeokgung_photo_content)[position]);
            photo_expand_contend.setBackgroundColor(getResources().getColor(R.color.toplayout_three_color));
        }

        close_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_text:
                finish();
                break;
        }
    }
}
