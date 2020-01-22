package com.funnytoday.seoul.seoulgo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.funnytoday.seoul.seoulgo.setting.CouponFragment1;
import com.funnytoday.seoul.seoulgo.setting.CouponFragment2;

/**
 * Created by 박상돈 on 2016-09-17.
 */
public class CouponPagerAdapter extends FragmentPagerAdapter {
    private int NUM_PAGES = 2;

    public CouponPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new CouponFragment1();
            case 1:
                return new CouponFragment2();

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return NUM_PAGES;
    }

}