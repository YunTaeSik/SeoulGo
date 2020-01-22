package com.funnytoday.seoul.seoulgo.setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.CouponAdapter;
import com.funnytoday.seoul.seoulgo.dialog.CouponDeleteDialog;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;

/**
 * Created by YunTaeSik on 2016-09-17.
 */
public class CouponFragment1 extends Fragment {
    private CouponAdapter couponAdapter;
    private GridView coupon_grid;
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private LinearLayout nocoupon_layout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContext().registerReceiver(broadcastReceiver, new IntentFilter(Contact.DELETE_COUPON));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.coupon_page_frgment1, container, false);
        coupon_grid = (GridView) layout.findViewById(R.id.coupon_grid);
        nocoupon_layout = (LinearLayout) layout.findViewById(R.id.nocoupon_layout);
        helper = new DBHelper(getContext());
        liteDatabase = helper.getWritableDatabase();
        Cursor cursor = liteDatabase.query(Contact.COUPON, null, null, null, null, null, null);
        couponAdapter = new CouponAdapter(getContext(), cursor.getCount());
        coupon_grid.setAdapter(couponAdapter);
        if (cursor.getCount() == 0) {
            nocoupon_layout.setVisibility(View.VISIBLE);
        } else {
            nocoupon_layout.setVisibility(View.GONE);
        }
        cursor.close();
        coupon_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), CouponDeleteDialog.class);
                intent.putExtra("position", position);
                getContext().startActivity(intent);
            }
        });

        return layout;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Contact.DELETE_COUPON)) {
                Log.e("delete", "delete");
                couponAdapter.notifyDataSetChanged();
                couponAdapter.notifyDataSetInvalidated();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}
