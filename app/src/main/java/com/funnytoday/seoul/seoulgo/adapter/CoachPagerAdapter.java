package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Note on 2016-10-11.
 */
public class CoachPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private Context context;
    private TextView coach_view_count;
    private int[] coachimage = {R.drawable.coach_image_one, R.drawable.coach_image_two, R.drawable.coach_image_three, R.drawable.coach_image_four};

    public CoachPagerAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.viewpager_childview, null);
        ImageView img = (ImageView) view.findViewById(R.id.img_viewpager_childimage);
        coach_view_count = (TextView) view.findViewById(R.id.coach_view_count);
        Picasso.with(context).load(coachimage[position]).fit().into(img);
        coach_view_count.setText(String.valueOf(position + 1) + "/" + String.valueOf(getCount()));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }
}
