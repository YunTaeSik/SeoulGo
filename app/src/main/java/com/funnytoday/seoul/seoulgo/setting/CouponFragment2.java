package com.funnytoday.seoul.seoulgo.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.AllianceAdapter;

/**
 * Created by YunTaeSik on 2016-09-17.
 */
public class CouponFragment2 extends Fragment implements View.OnTouchListener {
    private GridView alliance_grid, alliance_grid_two, alliance_grid_three;
    private GridView coupon_gride;
    private ScrollView scrollView;
    private TextView alliance_text;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.coupon_page_frgment2, container, false);
       /* coupon_gride = (GridView) layout.findViewById(R.id.coupon_gride);
        coupon_gride.setAdapter(new NewAllianceAdapter(getContext()));*/
        scrollView = (ScrollView) layout.findViewById(R.id.scrollView);
        alliance_grid = (GridView) layout.findViewById(R.id.alliance_grid);
        alliance_grid_two = (GridView) layout.findViewById(R.id.alliance_grid_two);
        alliance_grid_three = (GridView) layout.findViewById(R.id.alliance_grid_three);
        alliance_text = (TextView) layout.findViewById(R.id.alliance_text);

        alliance_text.setText(getString(R.string.place_two) + "," + getString(R.string.place_four));

        alliance_grid.setAdapter(new AllianceAdapter(getContext(), "경복궁"));
        alliance_grid_two.setAdapter(new AllianceAdapter(getContext(), "창경궁"));
        alliance_grid_three.setAdapter(new AllianceAdapter(getContext(), "덕수궁"));

        alliance_grid.setOnTouchListener(this);
        alliance_grid_two.setOnTouchListener(this);
        alliance_grid_three.setOnTouchListener(this);
        return layout;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        scrollView.requestDisallowInterceptTouchEvent(true);
        return false;
    }
}