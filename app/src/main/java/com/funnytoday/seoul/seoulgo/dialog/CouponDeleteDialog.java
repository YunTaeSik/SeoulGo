package com.funnytoday.seoul.seoulgo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.squareup.picasso.Picasso;

/**
 * Created by YunTaeSik on 2016-09-20.
 */
public class CouponDeleteDialog extends Activity implements View.OnClickListener {
    private ImageView coupon_image;
    private Button ok_btn;
    private Button cancle_btn;
    private int position;
    private String location;

    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_coupondeletedialog);
        setFinishOnTouchOutside(false);

        helper = new DBHelper(this);
        liteDatabase = helper.getWritableDatabase();
        position = getIntent().getIntExtra("position", 0);
        cursor = liteDatabase.query(Contact.COUPON, null, null, null, null, null, null);
        cursor.moveToPosition(position);
        location = cursor.getString(1);
        if (location.equals("경복궁")) {
            Picasso.with(getApplicationContext()).load(R.drawable.gyeongbokgung_coupon);
        } else if (location.equals("창경궁")) {
            Picasso.with(getApplicationContext()).load(R.drawable.changgyeonggung_coupon);
        } else if (location.equals("덕수궁")) {
            Picasso.with(getApplicationContext()).load(R.drawable.deoksugung_coupon);
        }

        coupon_image = (ImageView) findViewById(R.id.coupon_image);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        cancle_btn = (Button) findViewById(R.id.cancle_btn);

        ok_btn.setOnClickListener(this);
        cancle_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_btn:
                liteDatabase.delete(Contact.COUPON, "_id=?", new String[]{cursor.getString(0)});
                sendBroadcast(new Intent(Contact.DELETE_COUPON));
                finish();
                break;
            case R.id.cancle_btn:
                finish();
                break;

        }
    }
}