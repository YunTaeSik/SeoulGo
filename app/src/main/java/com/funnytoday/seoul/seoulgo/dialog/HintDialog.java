package com.funnytoday.seoul.seoulgo.dialog;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;

/**
 * Created by YunTaeSik on 2016-09-29.
 */
public class HintDialog extends Activity implements View.OnClickListener {
    private int[] hint_hide_view = {R.id.one_view, R.id.two_view, R.id.three_view, R.id.four_view, R.id.five_view, R.id.six_view};
    private int[] hint_text_view = {R.id.hint_text_one, R.id.hint_text_two, R.id.hint_text_three,
            R.id.hint_text_four, R.id.hint_text_five, R.id.hint_text_six};

    private View[] hide_view = new View[6];
    private TextView[] hint_text = new TextView[6];
    private Animation hint_alpha;
    private String location;
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_hint);

        helper = new DBHelper(this);
        liteDatabase = helper.getWritableDatabase();
        location = getIntent().getStringExtra("LOCATION");

        for (int i = 0; i < hint_hide_view.length; i++) {
            hide_view[i] = (View) findViewById(hint_hide_view[i]);
            hint_text[i] = (TextView) findViewById(hint_text_view[i]);
            hide_view[i].setOnClickListener(this);
        }

        hint_alpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hint_alpha);

        setHintView();
    }

    private void setHintView() {
        Cursor cursor = null;
        try {
            if (location.equals("경복궁")) {
                cursor = liteDatabase.query(Contact.USER_TABLE_NAME[1], null, null, null, null, null, null);
                for (int i = 0; i < 6; i++) {
                    cursor.moveToNext();
                    //hint_text[i].setText(Contact.SinHan[Integer.parseInt(cursor.getString(1))]);
                    hint_text[i].setText((getResources().getStringArray(R.array.gyeongbokgung_location)[Integer.parseInt(cursor.getString(1))]));
                    // hint_text[i].setText((getResources().getStringArray(R.array.gyeongbokgung_location)[Integer.parseInt(cursor.getString(1))]));
                }
            } else if (location.equals("창경궁")) {
                cursor = liteDatabase.query(Contact.USER_TABLE_NAME[2], null, null, null, null, null, null);
                for (int i = 0; i < 6; i++) {
                    cursor.moveToNext();
                    // hint_text[i].setText(Contact.Changgyeonggung[Integer.parseInt(cursor.getString(1))]);
                    hint_text[i].setText((getResources().getStringArray(R.array.changgyeonggung_location)[Integer.parseInt(cursor.getString(1))]));
                }
            } else if (location.equals("덕수궁")) {
                cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);
                for (int i = 0; i < 6; i++) {
                    cursor.moveToNext();
                    // hint_text[i].setText(Contact.Deoksugung[Integer.parseInt(cursor.getString(1))]);
                    hint_text[i].setText((getResources().getStringArray(R.array.deoksugung_location)[Integer.parseInt(cursor.getString(1))]));
                }
            } else if (location.equals("창덕궁")) {
                cursor = liteDatabase.query(Contact.USER_TABLE_NAME[4], null, null, null, null, null, null);
                for (int i = 0; i < 6; i++) {
                    cursor.moveToNext();
                    // hint_text[i].setText(Contact.Deoksugung[Integer.parseInt(cursor.getString(1))]);
                    hint_text[i].setText((getResources().getStringArray(R.array.changdeokgung_location)[Integer.parseInt(cursor.getString(1))]));
                }
            }
        } catch (Exception e) {

        } finally {
            cursor.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_view:
                hide_view[0].startAnimation(hint_alpha);
                hide_view[0].setVisibility(View.INVISIBLE);
                break;
            case R.id.two_view:
                hide_view[1].startAnimation(hint_alpha);
                hide_view[1].setVisibility(View.INVISIBLE);
                break;
            case R.id.three_view:
                hide_view[2].startAnimation(hint_alpha);
                hide_view[2].setVisibility(View.INVISIBLE);
                break;
            case R.id.four_view:
                hide_view[3].startAnimation(hint_alpha);
                hide_view[3].setVisibility(View.INVISIBLE);
                break;
            case R.id.five_view:
                hide_view[4].startAnimation(hint_alpha);
                hide_view[4].setVisibility(View.INVISIBLE);
                break;
            case R.id.six_view:
                hide_view[5].startAnimation(hint_alpha);
                hide_view[5].setVisibility(View.INVISIBLE);
                break;

        }
    }
}
