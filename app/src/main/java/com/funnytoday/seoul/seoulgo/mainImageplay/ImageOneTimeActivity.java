package com.funnytoday.seoul.seoulgo.mainImageplay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;


/**
 * Created by 박상돈 on 2016-09-09.
 */
public class ImageOneTimeActivity extends Activity {
    private String location;
    private TextView place_one_time_explain;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.image_one_time);
        setFinishOnTouchOutside(true);

        location = getIntent().getStringExtra("LOCATION"); // 경복궁 , 창경궁, 덕수궁


        place_one_time_explain = (TextView) findViewById(R.id.place_one_time_explain);

        if (location.equals("경복궁")) {

            place_one_time_explain.setText(getString(R.string.place_one_explain_time));


        } else if (location.equals("창경궁")) {

            place_one_time_explain.setText(getString(R.string.place_two_explain_time));


        } else if (location.equals("덕수궁")) {
            place_one_time_explain.setText(getString(R.string.place_three_explain_time));


        } else if (location.equals("창덕궁")) {
            place_one_time_explain.setText(getString(R.string.place_four_explain_time));
        }
    }
}