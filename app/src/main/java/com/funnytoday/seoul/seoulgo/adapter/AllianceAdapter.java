package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.mainImageplay.AllianceMapActivity;
import com.funnytoday.seoul.seoulgo.util.Contact;

/**
 * Created by YunTaeSik on 2016-09-28.
 */
public class AllianceAdapter extends BaseAdapter {
    private Context context;

    private String tag;

    public AllianceAdapter(Context context, String tag) {
        this.context = context;
        this.tag = tag;

    }

    @Override
    public int getCount() {
        if (tag.equals("경복궁")) {
            return 4;
        } else if (tag.equals("덕수궁")) {
            return 3;
        } else if (tag.equals("창경궁")) {
            return 1;
        } else {
            return 5;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.alliance_list, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.alliance_layout = (LinearLayout) convertView.findViewById(R.id.alliance_layout);
        viewHolder.alliance_title = (TextView) convertView.findViewById(R.id.alliance_title);
        viewHolder.alliance_content = (TextView) convertView.findViewById(R.id.alliance_content);
        viewHolder.map_image = (ImageView) convertView.findViewById(R.id.map_image);

        GradientDrawable bgShape = (GradientDrawable) viewHolder.alliance_layout.getBackground();
        bgShape.setColor(Color.parseColor(Contact.AllianceColor[position]));
        viewHolder.alliance_content.setTextColor(Color.parseColor(Contact.AllianceColor[position]));
        if (tag.equals("경복궁")) {
            viewHolder.alliance_title.setText(context.getResources().getStringArray(R.array.gyeongbokgung_store)[position]);
            viewHolder.alliance_content.setText(context.getResources().getStringArray(R.array.gyeongbokgung_coupon_content)[position]);
        } else if (tag.equals("덕수궁")) {
            viewHolder.alliance_title.setText(context.getResources().getStringArray(R.array.deoksugung_store)[position]);
            viewHolder.alliance_content.setText(context.getResources().getStringArray(R.array.deoksugung_coupon_content)[position]);
        } else if (tag.equals("창경궁")) {
            viewHolder.alliance_title.setText(context.getResources().getStringArray(R.array.changgyeonggung_store)[position]);
            viewHolder.alliance_content.setText(context.getResources().getStringArray(R.array.changgyeonggung_coupon_content)[position]);
        } else {
            viewHolder.alliance_title.setText(Contact.AllianceText[position]);
        }
        startMap(tag, position, viewHolder.map_image);
        return convertView;
    }

    private void startMap(final String tag, final int position, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AllianceMapActivity.class);
                intent.putExtra("tag", tag);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

    }

    private class ViewHolder {
        private LinearLayout alliance_layout;
        private TextView alliance_title;
        private TextView alliance_content;
        private ImageView map_image;
    }

}
