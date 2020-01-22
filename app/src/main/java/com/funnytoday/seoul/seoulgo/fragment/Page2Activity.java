package com.funnytoday.seoul.seoulgo.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.animation.HomeActivitySetting;
import com.funnytoday.seoul.seoulgo.function.CameraActivity;
import com.funnytoday.seoul.seoulgo.mainImageplay.ImageOneMapActivity;
import com.funnytoday.seoul.seoulgo.mainImageplay.ImageOnePriceActivity;
import com.funnytoday.seoul.seoulgo.mainImageplay.ImageOneTimeActivity;
import com.squareup.picasso.Picasso;

import java.util.Locale;


/* Page2Activity.java */
public class Page2Activity extends Fragment implements View.OnClickListener {

    public Fragment newInstance(String language) {  //언어 설졍 가져오는 로직
        Page2Activity global_language = new Page2Activity();
        Bundle args = new Bundle();
        args.putString("Language", language);
        global_language.setArguments(args);
        return global_language;
    }

    private String language;
    private String menuFlag1 = "on", menuFlag2 = "on", menuFlag3 = "on", menuFlag4 = "on";

    private ScrollView home_scroll;

    private LinearLayout place_one_explain, place_two_explain, place_three_explain, place_four_explain;

    private ImageView place_one_place, place_two_place, place_three_place, place_four_place;

    private LinearLayout place_one_btn1, place_one_btn4, place_one_btn7, place_one_btn10;
    private LinearLayout place_two_btn1, place_two_btn4, place_two_btn7, place_two_btn10;
    private LinearLayout place_four_btn1, place_four_btn4, place_four_btn7, place_four_btn10;
    private LinearLayout place_three_btn1, place_three_btn4, place_three_btn7, place_three_btn10;
    private ImageView place_one_btn2, place_one_btn5, place_one_btn8, place_one_btn11;
    private ImageView place_two_btn2, place_two_btn5, place_two_btn8, place_two_btn11;
    private ImageView place_four_btn2, place_four_btn5, place_four_btn8, place_four_btn11;
    private ImageView place_three_btn2, place_three_btn5, place_three_btn8, place_three_btn11;
    private TextView place_one_btn3, place_one_btn6, place_one_btn9, place_one_btn12;
    private TextView place_two_btn3, place_two_btn6, place_two_btn9, place_two_btn12;
    private TextView place_four_btn3, place_four_btn6, place_four_btn9, place_four_btn12;
    private TextView place_three_btn3, place_three_btn6, place_three_btn9, place_three_btn12;
    private TextView place_one_text, place_two_text, place_three_text, place_four_text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_page2, container, false);
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        home_scroll = (ScrollView) layout.findViewById(R.id.home_scroll);
        place_one_place = (ImageView) layout.findViewById(R.id.place_one_place);
        place_two_place = (ImageView) layout.findViewById(R.id.place_two_place);
        place_three_place = (ImageView) layout.findViewById(R.id.place_three_place);
        place_four_place = (ImageView) layout.findViewById(R.id.place_four_place);

        place_one_place.setOnClickListener(this);
        place_two_place.setOnClickListener(this);
        place_three_place.setOnClickListener(this);
        place_four_place.setOnClickListener(this);

        place_one_explain = (LinearLayout) layout.findViewById(R.id.place_one_explain);
        place_two_explain = (LinearLayout) layout.findViewById(R.id.place_two_explain);
        place_three_explain = (LinearLayout) layout.findViewById(R.id.place_three_explain);
        place_four_explain = (LinearLayout) layout.findViewById(R.id.place_four_explain);

        place_one_btn1 = (LinearLayout) layout.findViewById(R.id.place_one_btn1);
        place_one_btn4 = (LinearLayout) layout.findViewById(R.id.place_one_btn4);
        place_one_btn7 = (LinearLayout) layout.findViewById(R.id.place_one_btn7);
        place_one_btn10 = (LinearLayout) layout.findViewById(R.id.place_one_btn10);

        place_two_btn1 = (LinearLayout) layout.findViewById(R.id.place_two_btn1);
        place_two_btn4 = (LinearLayout) layout.findViewById(R.id.place_two_btn4);
        place_two_btn7 = (LinearLayout) layout.findViewById(R.id.place_two_btn7);
        place_two_btn10 = (LinearLayout) layout.findViewById(R.id.place_two_btn10);

        place_three_btn1 = (LinearLayout) layout.findViewById(R.id.place_three_btn1);
        place_three_btn4 = (LinearLayout) layout.findViewById(R.id.place_three_btn4);
        place_three_btn7 = (LinearLayout) layout.findViewById(R.id.place_three_btn7);
        place_three_btn10 = (LinearLayout) layout.findViewById(R.id.place_three_btn10);

        place_four_btn1 = (LinearLayout) layout.findViewById(R.id.place_four_btn1);
        place_four_btn4 = (LinearLayout) layout.findViewById(R.id.place_four_btn4);
        place_four_btn7 = (LinearLayout) layout.findViewById(R.id.place_four_btn7);
        place_four_btn10 = (LinearLayout) layout.findViewById(R.id.place_four_btn10);

        place_one_btn2 = (ImageView) layout.findViewById(R.id.place_one_btn2);
        place_one_btn5 = (ImageView) layout.findViewById(R.id.place_one_btn5);
        place_one_btn8 = (ImageView) layout.findViewById(R.id.place_one_btn8);
        place_one_btn11 = (ImageView) layout.findViewById(R.id.place_one_btn11);

        Picasso.with(getContext()).load(R.drawable.play).fit().into(place_one_btn2);
        Picasso.with(getContext()).load(R.drawable.map).fit().into(place_one_btn5);
        Picasso.with(getContext()).load(R.drawable.money).fit().into(place_one_btn8);
        Picasso.with(getContext()).load(R.drawable.clock).fit().into(place_one_btn11);

        place_two_btn2 = (ImageView) layout.findViewById(R.id.place_two_btn2);
        place_two_btn5 = (ImageView) layout.findViewById(R.id.place_two_btn5);
        place_two_btn8 = (ImageView) layout.findViewById(R.id.place_two_btn8);
        place_two_btn11 = (ImageView) layout.findViewById(R.id.place_two_btn11);

        Picasso.with(getContext()).load(R.drawable.play).fit().into(place_two_btn2);
        Picasso.with(getContext()).load(R.drawable.map).fit().into(place_two_btn5);
        Picasso.with(getContext()).load(R.drawable.money).fit().into(place_two_btn8);
        Picasso.with(getContext()).load(R.drawable.clock).fit().into(place_two_btn11);

        place_three_btn2 = (ImageView) layout.findViewById(R.id.place_three_btn2);
        place_three_btn5 = (ImageView) layout.findViewById(R.id.place_three_btn5);
        place_three_btn8 = (ImageView) layout.findViewById(R.id.place_three_btn8);
        place_three_btn11 = (ImageView) layout.findViewById(R.id.place_three_btn11);

        Picasso.with(getContext()).load(R.drawable.play).fit().into(place_three_btn2);
        Picasso.with(getContext()).load(R.drawable.map).fit().into(place_three_btn5);
        Picasso.with(getContext()).load(R.drawable.money).fit().into(place_three_btn8);
        Picasso.with(getContext()).load(R.drawable.clock).fit().into(place_three_btn11);

        place_four_btn2 = (ImageView) layout.findViewById(R.id.place_four_btn2);
        place_four_btn5 = (ImageView) layout.findViewById(R.id.place_four_btn5);
        place_four_btn8 = (ImageView) layout.findViewById(R.id.place_four_btn8);
        place_four_btn11 = (ImageView) layout.findViewById(R.id.place_four_btn11);


        Picasso.with(getContext()).load(R.drawable.play).fit().into(place_four_btn2);
        Picasso.with(getContext()).load(R.drawable.map).fit().into(place_four_btn5);
        Picasso.with(getContext()).load(R.drawable.money).fit().into(place_four_btn8);
        Picasso.with(getContext()).load(R.drawable.clock).fit().into(place_four_btn11);

        place_one_btn3 = (TextView) layout.findViewById(R.id.place_one_btn3);
        place_one_btn6 = (TextView) layout.findViewById(R.id.place_one_btn6);
        place_one_btn9 = (TextView) layout.findViewById(R.id.place_one_btn9);
        place_one_btn12 = (TextView) layout.findViewById(R.id.place_one_btn12);

        place_two_btn3 = (TextView) layout.findViewById(R.id.place_two_btn3);
        place_two_btn6 = (TextView) layout.findViewById(R.id.place_two_btn6);
        place_two_btn9 = (TextView) layout.findViewById(R.id.place_two_btn9);
        place_two_btn12 = (TextView) layout.findViewById(R.id.place_two_btn12);

        place_three_btn3 = (TextView) layout.findViewById(R.id.place_three_btn3);
        place_three_btn6 = (TextView) layout.findViewById(R.id.place_three_btn6);
        place_three_btn9 = (TextView) layout.findViewById(R.id.place_three_btn9);
        place_three_btn12 = (TextView) layout.findViewById(R.id.place_three_btn12);

        place_four_btn3 = (TextView) layout.findViewById(R.id.place_four_btn3);
        place_four_btn6 = (TextView) layout.findViewById(R.id.place_four_btn6);
        place_four_btn9 = (TextView) layout.findViewById(R.id.place_four_btn9);
        place_four_btn12 = (TextView) layout.findViewById(R.id.place_four_btn12);

        place_one_text = (TextView) layout.findViewById(R.id.place_one_text);
        place_two_text = (TextView) layout.findViewById(R.id.place_two_text);
        place_three_text = (TextView) layout.findViewById(R.id.place_three_text);
        place_four_text = (TextView) layout.findViewById(R.id.place_four_text);

        place_three_text.setOnClickListener(this);

        place_one_btn1.setOnClickListener(this);
        place_one_btn2.setOnClickListener(this);
        place_one_btn3.setOnClickListener(this);
        place_one_btn4.setOnClickListener(this);
        place_one_btn5.setOnClickListener(this);
        place_one_btn6.setOnClickListener(this);
        place_one_btn7.setOnClickListener(this);
        place_one_btn8.setOnClickListener(this);
        place_one_btn9.setOnClickListener(this);
        place_one_btn10.setOnClickListener(this);
        place_one_btn11.setOnClickListener(this);
        place_one_btn12.setOnClickListener(this);

        place_two_btn1.setOnClickListener(this);
        place_two_btn2.setOnClickListener(this);
        place_two_btn3.setOnClickListener(this);
        place_two_btn4.setOnClickListener(this);
        place_two_btn5.setOnClickListener(this);
        place_two_btn6.setOnClickListener(this);
        place_two_btn7.setOnClickListener(this);
        place_two_btn8.setOnClickListener(this);
        place_two_btn9.setOnClickListener(this);
        place_two_btn10.setOnClickListener(this);
        place_two_btn11.setOnClickListener(this);
        place_two_btn12.setOnClickListener(this);

        place_three_btn1.setOnClickListener(this);
        place_three_btn2.setOnClickListener(this);
        place_three_btn3.setOnClickListener(this);
        place_three_btn4.setOnClickListener(this);
        place_three_btn5.setOnClickListener(this);
        place_three_btn6.setOnClickListener(this);
        place_three_btn7.setOnClickListener(this);
        place_three_btn8.setOnClickListener(this);
        place_three_btn9.setOnClickListener(this);
        place_three_btn10.setOnClickListener(this);
        place_three_btn11.setOnClickListener(this);
        place_three_btn12.setOnClickListener(this);

        place_four_btn1.setOnClickListener(this);
        place_four_btn2.setOnClickListener(this);
        place_four_btn3.setOnClickListener(this);
        place_four_btn4.setOnClickListener(this);
        place_four_btn5.setOnClickListener(this);
        place_four_btn6.setOnClickListener(this);
        place_four_btn7.setOnClickListener(this);
        place_four_btn8.setOnClickListener(this);
        place_four_btn9.setOnClickListener(this);
        place_four_btn10.setOnClickListener(this);
        place_four_btn11.setOnClickListener(this);
        place_four_btn12.setOnClickListener(this);


        Picasso.with(getContext()).load(R.drawable.gyeongbokgung_image).fit().into(place_one_place);
        Picasso.with(getContext()).load(R.drawable.changgyeonggung_image).fit().into(place_two_place);
        Picasso.with(getContext()).load(R.drawable.deoksugung_image).fit().into(place_three_place);
        Picasso.with(getContext()).load(R.drawable.changdeokgung_image).fit().into(place_four_place);

        if (language.equals("ko")) {
            place_one_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "NanumBrush.ttf"));
            place_two_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "NanumBrush.ttf"));
            place_three_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "NanumBrush.ttf"));
            place_four_text.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "NanumBrush.ttf"));
        } else if (language.equals("ja")) {
            place_one_text.setTextSize(30);
            place_two_text.setTextSize(30);
            place_three_text.setTextSize(30);
            place_four_text.setTextSize(30);
        } else {
            place_one_text.setTextSize(30);
            place_two_text.setTextSize(30);
            place_three_text.setTextSize(30);
            place_four_text.setTextSize(30);
        }
        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.place_one_place:
                if (menuFlag1.equals("on")) {

                    HomeActivitySetting.HelpMenuOn(place_one_explain, menuFlag1);
                    menuFlag1 = "off";

                } else if (menuFlag1.equals("off")) {

                    HomeActivitySetting.HelpMenuOff(place_one_explain, menuFlag1);
                    menuFlag1 = "on";
                }
                break;
            case R.id.place_two_place:
                if (menuFlag2.equals("on")) {

                    HomeActivitySetting.HelpMenuOn(place_two_explain, menuFlag2);
                    menuFlag2 = "off";

                } else if (menuFlag2.equals("off")) {

                    HomeActivitySetting.HelpMenuOff(place_two_explain, menuFlag2);
                    menuFlag2 = "on";
                }
                break;
            case R.id.place_three_place:
            case R.id.place_three_text:
                home_scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        home_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                if (menuFlag3.equals("on")) {
                    HomeActivitySetting.HelpMenuOn(place_three_explain, menuFlag3);
                    menuFlag3 = "off";

                } else if (menuFlag3.equals("off")) {
                    HomeActivitySetting.HelpMenuOff(place_three_explain, menuFlag3);
                    menuFlag3 = "on";

                }
                break;
            case R.id.place_four_place:
            case R.id.place_four_text:
                home_scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        home_scroll.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });
                if (menuFlag4.equals("on")) {
                    HomeActivitySetting.HelpMenuOn(place_four_explain, menuFlag4);
                    menuFlag4 = "off";

                } else if (menuFlag4.equals("off")) {
                    HomeActivitySetting.HelpMenuOff(place_four_explain, menuFlag4);
                    menuFlag4 = "on";

                }
                break;
            case R.id.place_one_btn1:
            case R.id.place_one_btn2:
            case R.id.place_one_btn3:
                Intent camera = new Intent(getActivity(), CameraActivity.class);
                camera.putExtra("LOCATION", "경복궁");
                startActivity(camera);
                break;
            case R.id.place_one_btn4:
            case R.id.place_one_btn5:
            case R.id.place_one_btn6:
                Intent intent1 = new Intent(getActivity(), ImageOneMapActivity.class);
                intent1.putExtra("LOCATION", "경복궁");
                startActivity(intent1);
                break;

            case R.id.place_one_btn7:
            case R.id.place_one_btn8:
            case R.id.place_one_btn9:
                Intent intent2 = new Intent(getActivity(), ImageOnePriceActivity.class);
                intent2.putExtra("LOCATION", "경복궁");
                startActivity(intent2);
                break;
            case R.id.place_one_btn10:
            case R.id.place_one_btn11:
            case R.id.place_one_btn12:
                Intent intent3 = new Intent(getActivity(), ImageOneTimeActivity.class);
                intent3.putExtra("LOCATION", "경복궁");
                startActivity(intent3);
                break;
            case R.id.place_two_btn1:
            case R.id.place_two_btn2:
            case R.id.place_two_btn3:
                Intent camera2 = new Intent(getActivity(), CameraActivity.class);
                camera2.putExtra("LOCATION", "창경궁");
                startActivity(camera2);
                break;
            case R.id.place_two_btn4:
            case R.id.place_two_btn5:
            case R.id.place_two_btn6:
                Intent intent4 = new Intent(getActivity(), ImageOneMapActivity.class);
                intent4.putExtra("LOCATION", "창경궁");
                startActivity(intent4);
                break;
            case R.id.place_two_btn7:
            case R.id.place_two_btn8:
            case R.id.place_two_btn9:
                Intent intent5 = new Intent(getActivity(), ImageOnePriceActivity.class);
                intent5.putExtra("LOCATION", "창경궁");
                startActivity(intent5);
                break;
            case R.id.place_two_btn10:
            case R.id.place_two_btn11:
            case R.id.place_two_btn12:
                Intent intent6 = new Intent(getActivity(), ImageOneTimeActivity.class);
                intent6.putExtra("LOCATION", "창경궁");
                startActivity(intent6);
                break;
            case R.id.place_three_btn1:
            case R.id.place_three_btn2:
            case R.id.place_three_btn3:
                Intent camera3 = new Intent(getActivity(), CameraActivity.class);
                camera3.putExtra("LOCATION", "덕수궁");
                startActivity(camera3);
                break;
            case R.id.place_three_btn4:
            case R.id.place_three_btn5:
            case R.id.place_three_btn6:
                Intent intent7 = new Intent(getActivity(), ImageOneMapActivity.class);
                intent7.putExtra("LOCATION", "덕수궁");
                startActivity(intent7);
                break;
            case R.id.place_three_btn7:
            case R.id.place_three_btn8:
            case R.id.place_three_btn9:
                Intent intent8 = new Intent(getActivity(), ImageOnePriceActivity.class);
                intent8.putExtra("LOCATION", "덕수궁");
                startActivity(intent8);
                break;
            case R.id.place_three_btn10:
            case R.id.place_three_btn11:
            case R.id.place_three_btn12:
                Intent intent9 = new Intent(getActivity(), ImageOneTimeActivity.class);
                intent9.putExtra("LOCATION", "덕수궁");
                startActivity(intent9);
                break;
            case R.id.place_four_btn1:
            case R.id.place_four_btn2:
            case R.id.place_four_btn3:
                Intent camera4 = new Intent(getActivity(), CameraActivity.class);
                camera4.putExtra("LOCATION", "창덕궁");
                startActivity(camera4);
                break;
            case R.id.place_four_btn4:
            case R.id.place_four_btn5:
            case R.id.place_four_btn6:
                Intent intent10 = new Intent(getActivity(), ImageOneMapActivity.class);
                intent10.putExtra("LOCATION", "창덕궁");
                startActivity(intent10);
                break;
            case R.id.place_four_btn7:
            case R.id.place_four_btn8:
            case R.id.place_four_btn9:
                Intent intent11 = new Intent(getActivity(), ImageOnePriceActivity.class);
                intent11.putExtra("LOCATION", "창덕궁");
                startActivity(intent11);
                break;
            case R.id.place_four_btn10:
            case R.id.place_four_btn11:
            case R.id.place_four_btn12:
                Intent intent12 = new Intent(getActivity(), ImageOneTimeActivity.class);
                intent12.putExtra("LOCATION", "창덕궁");
                startActivity(intent12);
                break;

        }
    }

}
