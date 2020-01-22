package com.funnytoday.seoul.seoulgo.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.dialog.CollectionContentDialog;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.funnytoday.seoul.seoulgo.util.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by YunTaeSik on 2016-09-11.
 */
public class CollectionAdapter extends BaseAdapter {
    private Context context;
    // private TextView puzzle_name;
    // private TextView[] puzzleview = new TextView[Contact.PUZZLEVIEW_LIST.length];
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private Animation alphaAni;
    /*private Button coupon_btn;
    private LinearLayout collection_click_layout;
    private ImageView collection_image;
    private ImageView level_image;*/

    public CollectionAdapter(Context context) {
        this.context = context;
        helper = new DBHelper(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.collection_list, parent, false);

        alphaAni = AnimationUtils.loadAnimation(context, R.anim.alpha_master);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.puzzle_name = (TextView) convertView.findViewById(R.id.puzzle_name);
        viewHolder.coupon_btn = (Button) convertView.findViewById(R.id.coupon_btn);
        viewHolder.collection_click_layout = (LinearLayout) convertView.findViewById(R.id.collection_click_layout);
        viewHolder.collection_image = (ImageView) convertView.findViewById(R.id.collection_image);
        viewHolder.level_image = (ImageView) convertView.findViewById(R.id.level_image);

        for (int i = 0; i < Contact.PUZZLEVIEW_LIST.length; i++) {
            viewHolder.puzzleview[i] = (ImageView) convertView.findViewById(Contact.PUZZLEVIEW_LIST[i]);
            Picasso.with(context).load(R.drawable.puzzle_hide_background).fit().into(viewHolder.puzzleview[i]);
        }
        if (position == 0) {
            liteDatabase = helper.getWritableDatabase();
            int gyeongbokgung_count = 0;
            final int start_count = SharedPrefsUtils.getIntegerPreference(context, Contact.Gyeongbokgung_Count, 0);
            setLevel(start_count, viewHolder);
            Picasso.with(context).load(R.drawable.gyeongbokgung_image_converted).fit().into(viewHolder.collection_image);
            viewHolder.puzzle_name.setText(context.getString(R.string.place_one));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[1], null, null, null, null, null, null);
            Log.e("count_test", String.valueOf(cursor.getCount()));
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        viewHolder.puzzleview[count].setVisibility(View.INVISIBLE);
                        gyeongbokgung_count++;
                    } else if (cursor.getString(2).equals("off")) {
                        viewHolder.puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }

            if (gyeongbokgung_count == 6) {
                viewHolder.coupon_btn.setBackgroundColor(Color.parseColor("#B6846F"));
                viewHolder.coupon_btn.setText(context.getString(R.string.getcoupon_text));
                viewHolder.coupon_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "성공입니다.", Toast.LENGTH_SHORT).show();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("place", "경복궁");
                        liteDatabase.insert(Contact.COUPON, null, contentValues);
                        liteDatabase.delete(Contact.USER_TABLE_NAME[1], "puzzle=?", new String[]{"on"});
                        context.sendBroadcast(new Intent(Contact.COLLECTION_FINISH));
                        SharedPrefsUtils.setIntegerPreference(context, Contact.Gyeongbokgung_Count, start_count + 1);
                    }
                });
            } else {
                viewHolder.coupon_btn.setBackground(context.getResources().getDrawable(R.drawable.collection_btn_background));
                viewHolder.coupon_btn.setText(context.getString(R.string.processcoupon_text) + "(" + gyeongbokgung_count + "/6)");
            }
        } else if (position == 1) {
            liteDatabase = helper.getWritableDatabase();
            int changgyeonggung_count = 0;
            final int start_count = SharedPrefsUtils.getIntegerPreference(context, Contact.Changgyeonggung_Count, 0);
            setLevel(start_count, viewHolder);
            // collection_layout.setBackground(context.getResources().getDrawable(R.drawable.changgyeonggung_image));
            Picasso.with(context).load(R.drawable.changgyeonggung_image).fit().into(viewHolder.collection_image);
            viewHolder.puzzle_name.setText(context.getString(R.string.place_two));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[2], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        viewHolder.puzzleview[count].setVisibility(View.INVISIBLE);
                        changgyeonggung_count++;
                    } else if (cursor.getString(2).equals("off")) {
                        viewHolder.puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
            if (changgyeonggung_count == 6) {
                viewHolder.coupon_btn.setBackgroundColor(Color.parseColor("#B6846F"));
                viewHolder.coupon_btn.setText(context.getString(R.string.getcoupon_text));
                viewHolder.coupon_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("place", "창경궁");
                        liteDatabase.insert(Contact.COUPON, null, contentValues);
                        liteDatabase.delete(Contact.USER_TABLE_NAME[2], "puzzle=?", new String[]{"on"});
                        context.sendBroadcast(new Intent(Contact.COLLECTION_FINISH));
                        SharedPrefsUtils.setIntegerPreference(context, Contact.Changgyeonggung_Count, start_count + 1);
                    }
                });
            } else {
                viewHolder.coupon_btn.setBackground(context.getResources().getDrawable(R.drawable.collection_btn_background));
                viewHolder.coupon_btn.setText(context.getString(R.string.processcoupon_text) + "(" + changgyeonggung_count + "/6)");
            }
        } else if (position == 2) {
            liteDatabase = helper.getWritableDatabase();
            int deoksugung_count = 0;
            final int start_count = SharedPrefsUtils.getIntegerPreference(context, Contact.Deoksugung_Count, 0);
            setLevel(start_count, viewHolder);
            //  collection_layout.setBackground(context.getResources().getDrawable(R.drawable.deoksugung_image));
            Picasso.with(context).load(R.drawable.deoksugung_image).fit().into(viewHolder.collection_image);
            viewHolder.puzzle_name.setText(context.getString(R.string.place_three));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        viewHolder.puzzleview[count].setVisibility(View.INVISIBLE);
                        deoksugung_count++;
                    } else if (cursor.getString(2).equals("off")) {
                        viewHolder.puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
            if (deoksugung_count == 6) {
                viewHolder.coupon_btn.setBackgroundColor(Color.parseColor("#B6846F"));
                viewHolder.coupon_btn.setText(context.getString(R.string.getcoupon_text));
                viewHolder.coupon_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("place", "덕수궁");
                        liteDatabase.insert(Contact.COUPON, null, contentValues);
                        liteDatabase.delete(Contact.USER_TABLE_NAME[3], "puzzle=?", new String[]{"on"});
                        context.sendBroadcast(new Intent(Contact.COLLECTION_FINISH));
                        SharedPrefsUtils.setIntegerPreference(context, Contact.Deoksugung_Count, start_count + 1);
                    }
                });
            } else {
                viewHolder.coupon_btn.setBackground(context.getResources().getDrawable(R.drawable.collection_btn_background));
                viewHolder.coupon_btn.setText(context.getString(R.string.processcoupon_text) + "(" + deoksugung_count + "/6)");
            }
        } else if (position == 3) {
            liteDatabase = helper.getWritableDatabase();
            int changdeokgung_count = 0;
            final int start_count = SharedPrefsUtils.getIntegerPreference(context, Contact.Changdeokgung_Count, 0);
            setLevel(start_count, viewHolder);
            //  collection_layout.setBackground(context.getResources().getDrawable(R.drawable.deoksugung_image));
            Picasso.with(context).load(R.drawable.changdeokgung_image).fit().into(viewHolder.collection_image);
            viewHolder.puzzle_name.setText(context.getString(R.string.place_four));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[4], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        viewHolder.puzzleview[count].setVisibility(View.INVISIBLE);
                        changdeokgung_count++;
                    } else if (cursor.getString(2).equals("off")) {
                        viewHolder.puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
            if (changdeokgung_count == 6) {
                viewHolder.coupon_btn.setBackgroundColor(Color.parseColor("#B6846F"));
                viewHolder.coupon_btn.setText(context.getString(R.string.getcoupon_text));
                viewHolder.coupon_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("place", "창덕궁");
                        liteDatabase.insert(Contact.COUPON, null, contentValues);
                        liteDatabase.delete(Contact.USER_TABLE_NAME[4], "puzzle=?", new String[]{"on"});
                        context.sendBroadcast(new Intent(Contact.COLLECTION_FINISH));
                        SharedPrefsUtils.setIntegerPreference(context, Contact.Changdeokgung_Count, start_count + 1);
                    }
                });
            } else {
                viewHolder.coupon_btn.setBackground(context.getResources().getDrawable(R.drawable.collection_btn_background));
                viewHolder.coupon_btn.setText(context.getString(R.string.processcoupon_text) + "(" + changdeokgung_count + "/6)");
            }
        }


        viewHolder.collection_click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        Intent intent = new Intent(context, CollectionContentDialog.class);
                        intent.putExtra("LOCATION", "경복궁");
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(context, CollectionContentDialog.class);
                        intent2.putExtra("LOCATION", "창경궁");
                        context.startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(context, CollectionContentDialog.class);
                        intent3.putExtra("LOCATION", "덕수궁");
                        context.startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(context, CollectionContentDialog.class);
                        intent4.putExtra("LOCATION", "창덕궁");
                        context.startActivity(intent4);
                        break;
                }
            }
        });
        return convertView;
    }

    private void setLevel(int start_count, ViewHolder viewHolder) {
        String color = "#ffffff";
        switch (start_count) {
            case 1:
                color = "#cd7f32";
                break;
            case 2:
                color = "#c0c0c0";
                break;
            case 3:
                color = "#ffd700";
                break;
        }
        if (start_count > 0 && start_count <= 3) {
            viewHolder.level_image.setVisibility(View.VISIBLE);
            viewHolder.level_image.setColorFilter(Color.parseColor(color));
        } else if (start_count > 3 && start_count <= 4) {
            viewHolder.level_image.setVisibility(View.VISIBLE);
            viewHolder.level_image.setImageResource(R.drawable.diamond_image);
        } else if (start_count > 4) {
            viewHolder.level_image.setVisibility(View.VISIBLE);
            viewHolder.level_image.setImageResource(R.drawable.master_image_converted);
            viewHolder.level_image.startAnimation(alphaAni);
        }
    }

    private class ViewHolder {
        TextView puzzle_name;
        ImageView[] puzzleview = new ImageView[Contact.PUZZLEVIEW_LIST.length];
        Button coupon_btn;
        LinearLayout collection_click_layout;
        ImageView collection_image;
        ImageView level_image;
    }

}
