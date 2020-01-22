package com.funnytoday.seoul.seoulgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.PhotoPagerAdapter;
import com.funnytoday.seoul.seoulgo.function.PhotoExpandActivity;


/* Page3Activity.java */
public class Page3Activity extends Fragment implements AdapterView.OnItemClickListener {
    private ViewPager gyeongbokgung_photo;
    private ViewPager changgyeonggung_photo;
    private ViewPager deoksugung_photo;
    private ViewPager changdeokgung_photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_page3, container, false);
        gyeongbokgung_photo = (ViewPager) layout.findViewById(R.id.gyeongbokgung_photo);
        changgyeonggung_photo = (ViewPager) layout.findViewById(R.id.changgyeonggung_photo);
        deoksugung_photo = (ViewPager) layout.findViewById(R.id.deoksugung_photo);
        changdeokgung_photo = (ViewPager) layout.findViewById(R.id.changdeokgung_photo);

        gyeongbokgung_photo.setAdapter(new PhotoPagerAdapter(getContext(), "경복궁"));
        changgyeonggung_photo.setAdapter(new PhotoPagerAdapter(getContext(), "창경궁"));
        deoksugung_photo.setAdapter(new PhotoPagerAdapter(getContext(), "덕수궁"));
        changdeokgung_photo.setAdapter(new PhotoPagerAdapter(getContext(), "창덕궁"));

        return layout;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.gyeongbokgung_photo:
                Expand("경복궁", position);
                break;
            case R.id.changgyeonggung_photo:
                Expand("창경궁", position);
                break;
            case R.id.deoksugung_photo:
                Expand("덕수궁", position);
                break;
            case R.id.changdeokgung_photo:
                Expand("창덕궁", position);
                break;
        }
    }

    private void Expand(String tag, int postion) {
        Intent intent = new Intent(getContext(), PhotoExpandActivity.class);
        intent.putExtra("tag", tag);
        intent.putExtra("postion", postion);
        startActivity(intent);
    }
}
