package com.funnytoday.seoul.seoulgo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by YunTaeSik on 2016-09-18.
 */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private int count;

    public CouponAdapter(Context context, int count) {
        this.context = context;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
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
        convertView = mInflater.inflate(R.layout.coupon_list, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.coupon_image = (ImageView) convertView.findViewById(R.id.coupon_image);
        viewHolder.coupon_name_text = (TextView) convertView.findViewById(R.id.coupon_name_text);
        helper = new DBHelper(context);
        liteDatabase = helper.getWritableDatabase();
        Cursor cursor = liteDatabase.query(Contact.COUPON, null, null, null, null, null, null);
        try {
            cursor.moveToPosition(position);
            String tag = cursor.getString(1);
            if (tag.equals("경복궁")) {
                Picasso.with(context).load(R.drawable.gyeongbokgung_coupon).fit().into(viewHolder.coupon_image);
                viewHolder.coupon_name_text.setText(context.getString(R.string.place_one));
            } else if (tag.equals("창경궁")) {
                Picasso.with(context).load(R.drawable.changgyeonggung_coupon).fit().into(viewHolder.coupon_image);
                viewHolder.coupon_name_text.setText(context.getString(R.string.place_two));
                viewHolder.coupon_name_text.setTextColor(Color.parseColor("#000000"));
            } else if (tag.equals("덕수궁")) {
                Picasso.with(context).load(R.drawable.deoksugung_coupon).fit().into(viewHolder.coupon_image);
                viewHolder.coupon_name_text.setText(context.getString(R.string.place_three));
            } else if (tag.equals("창덕궁")) {
                Picasso.with(context).load(R.drawable.changgyeonggung_coupon).fit().into(viewHolder.coupon_image);
                viewHolder.coupon_name_text.setText(context.getString(R.string.place_four));
                viewHolder.coupon_name_text.setTextColor(Color.parseColor("#000000"));
            }
        } catch (CursorIndexOutOfBoundsException e) {
        } finally {
            cursor.close();
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView coupon_image;
        TextView coupon_name_text;
    }
}
