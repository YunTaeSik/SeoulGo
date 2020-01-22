package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;

/**
 * Created by YunTaeSik on 2016-10-29.
 */
public class MiniMapAdapter extends BaseAdapter {
    private Context context;
    private String tag;

    public MiniMapAdapter(Context context, String tag) {
        this.context = context;
        this.tag = tag;

    }

    @Override
    public int getCount() {
        if (tag.equals("경복궁")) {
            return 14;
        } else if (tag.equals("창경궁")) {
            return 26;
        } else if (tag.equals("덕수궁")) {
            return 9;
        } else if (tag.equals("창덕궁")) {
            return 8;
        }
        return 0;
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
        convertView = mInflater.inflate(R.layout.minimap_number_list, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.number_text = (TextView) convertView.findViewById(R.id.number_text);
        if (tag.equals("경복궁")) {
            viewHolder.number_text.setText(String.valueOf((position + 1) + ".") +
                    (context.getResources().getStringArray(R.array.gyeongbokgung_minimap_location))[position]);
        } else if (tag.equals("창경궁")) {
            viewHolder.number_text.setText(String.valueOf((position + 1) + ".") +
                    (context.getResources().getStringArray(R.array.changgyeonggung_minimap_location))[position]);
        } else if (tag.equals("덕수궁")) {
            viewHolder.number_text.setText(String.valueOf((position + 1) + ".") +
                    (context.getResources().getStringArray(R.array.deoksugung_minimap_location))[position]);
        } else if (tag.equals("창덕궁")) {
            viewHolder.number_text.setText(String.valueOf((position + 1) + ".") +
                    (context.getResources().getStringArray(R.array.changdeokgung_minimap_location))[position]);
        }
        return convertView;
    }

    private class ViewHolder {
        private TextView number_text;
    }
}
