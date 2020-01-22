package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;

/**
 * Created by YunTaeSik on 2016-10-24.
 */
public class NewAllianceAdapter extends BaseAdapter {
    private Context context;
    private int[] title = {R.string.place_one, R.string.place_two, R.string.place_three, R.string.place_four};
    private String[] tag = {"경복궁", "창경궁", "덕수궁", "창덕궁"};

    public NewAllianceAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return 4;
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
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.alliance_new_list, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.alliance_grid = (GridView) convertView.findViewById(R.id.alliance_grid);
        viewHolder.title_text = (TextView) convertView.findViewById(R.id.title_text);
        viewHolder.title_text.setText(title[position]);
        viewHolder.alliance_grid.setAdapter(new AllianceAdapter(context, tag[position]));

        return convertView;
    }

    private class ViewHolder {
        GridView alliance_grid;
        TextView title_text;
    }
}
