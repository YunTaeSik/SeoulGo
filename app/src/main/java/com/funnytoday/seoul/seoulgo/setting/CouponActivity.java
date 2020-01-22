package com.funnytoday.seoul.seoulgo.setting;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.CouponPagerAdapter;


/**
 * Created by 박상돈 on 2016-09-04.
 */
public class CouponActivity extends FragmentActivity implements View.OnClickListener {

    public final static int FRAGMENT_PAGE1 = 0;
    public final static int FRAGMENT_PAGE2 = 1;
    private ViewPager CouponPager;
    private Button coupon_btn1, coupon_btn2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CouponPager = (ViewPager) findViewById(R.id.coupon_pager);
        CouponPager.setAdapter(new CouponPagerAdapter(getSupportFragmentManager()));
        CouponPager.setCurrentItem(FRAGMENT_PAGE1);

        coupon_btn1 = (Button) findViewById(R.id.coupon_btn1);
        coupon_btn2 = (Button) findViewById(R.id.coupon_btn2);

        coupon_btn1.setOnClickListener(this);
        coupon_btn2.setOnClickListener(this);

        CouponPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                coupon_btn1.setSelected(false);
                coupon_btn2.setSelected(false);

                switch (position) {
                    case 0:
                        coupon_btn1.setSelected(true);
                        break;
                    case 1:
                        coupon_btn2.setSelected(true);
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

        coupon_btn1.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coupon_btn1:
                CouponPager.setCurrentItem(FRAGMENT_PAGE1);
                break;
            case R.id.coupon_btn2:
                CouponPager.setCurrentItem(FRAGMENT_PAGE2);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
