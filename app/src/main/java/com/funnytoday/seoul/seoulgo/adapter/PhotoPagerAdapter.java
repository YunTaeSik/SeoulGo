package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.function.PhotoExpandActivity;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by YunTaeSik on 2016-09-11.
 */
public class PhotoPagerAdapter extends PagerAdapter {
    private View viewpager;
    private Context context;
    private String tag;


    public PhotoPagerAdapter(Context context, String tag) {
        this.context = context;
        this.tag = tag;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        viewpager = LayoutInflater.from(context).inflate(R.layout.photo_list, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.one_count = (TextView) viewpager.findViewById(R.id.one_count);
        viewHolder.top_layout = (LinearLayout) viewpager.findViewById(R.id.top_layout);
        viewHolder.content_text = (TextView) viewpager.findViewById(R.id.content_text);
        viewHolder.viewpager_image = (ImageView) viewpager.findViewById(R.id.viewpager_image);
        viewHolder.one_count.setText(position + 1 + "/" + getCount());

        if (tag.equals("경복궁")) {
            viewHolder.top_layout.setBackgroundColor(context.getResources().getColor(R.color.theme));
            viewHolder.content_text.setText(context.getString(R.string.viewpager_content_one));
            viewHolder.content_text.setText(context.getResources().getStringArray(R.array.gyeongbokgung_photo_content)[position]);
            Picasso.with(context).load(Contact.Gyeongbokgung_PhotoZone[position]).fit().into(viewHolder.viewpager_image);
            viewHolder.one_count.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.viewpager_count_background));
        } else if (tag.equals("창경궁")) {
            viewHolder.top_layout.setBackgroundColor(context.getResources().getColor(R.color.toplayout_one_color));
            viewHolder.content_text.setText(context.getString(R.string.viewpager_content_two));
            Picasso.with(context).load(Contact.Changgyeonggung_PhotoZone[position]).fit().into(viewHolder.viewpager_image);
            viewHolder.one_count.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.viewpager_count_two_background));
            viewHolder.content_text.setText(context.getResources().getStringArray(R.array.changgyeonggung_photo_content)[position]);
        } else if (tag.equals("덕수궁")) {
            viewHolder.top_layout.setBackgroundColor(context.getResources().getColor(R.color.toplayout_two_color));
            viewHolder.content_text.setText(context.getString(R.string.viewpager_content_three));
            Picasso.with(context).load(Contact.Deoksugung_PhotoZone[position]).fit().into(viewHolder.viewpager_image);
            viewHolder.one_count.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.viewpager_count_three_background));
            viewHolder.content_text.setText(context.getResources().getStringArray(R.array.deoksugung_photo_content)[position]);
        } else if (tag.equals("창덕궁")) {
            viewHolder.top_layout.setBackgroundColor(context.getResources().getColor(R.color.toplayout_three_color));
            viewHolder.content_text.setText(context.getString(R.string.viewpager_content_four));
            Picasso.with(context).load(Contact.Changdeokgung_PhotoZone[position]).fit().into(viewHolder.viewpager_image);
            viewHolder.one_count.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.viewpager_count_four_background));
            viewHolder.content_text.setText(context.getResources().getStringArray(R.array.changdeokgung_photo_content)[position]);
        }

        Expand(tag, position, viewHolder.viewpager_image);

        container.addView(viewpager);
        return viewpager;
    }

    private void Expand(final String tag, final int position, ImageView image) {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotoExpandActivity.class);
                intent.putExtra("tag", tag);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return 0.85f;
    }

    private class ViewHolder {
        TextView one_count;
        LinearLayout top_layout;
        TextView content_text;
        ImageView viewpager_image;
    }
}
