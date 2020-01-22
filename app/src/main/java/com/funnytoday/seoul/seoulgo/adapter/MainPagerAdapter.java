package com.funnytoday.seoul.seoulgo.adapter;

/**
 * Created by 박상돈 on 2016-08-29.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.funnytoday.seoul.seoulgo.fragment.Page1Activity;
import com.funnytoday.seoul.seoulgo.fragment.Page2Activity;
import com.funnytoday.seoul.seoulgo.fragment.Page3Activity;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private int NUM_PAGES = 3;
    private String language;

    public MainPagerAdapter(android.support.v4.app.FragmentManager fm, String language) {
        super(fm);
        this.language = language;
    }


    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new Page1Activity();
            case 1:
                return new Page2Activity().newInstance(language); //언어 가져오기
            case 2:
                return new Page3Activity();
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



