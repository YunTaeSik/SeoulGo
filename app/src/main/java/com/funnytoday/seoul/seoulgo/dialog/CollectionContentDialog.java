package com.funnytoday.seoul.seoulgo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YunTaeSik on 2016-09-13.
 */
public class CollectionContentDialog extends Activity implements View.OnClickListener {
    private Button close_btn;
    private String LOCATION;
    private TextView[] puzzleview = new TextView[Contact.PUZZLEVIEW_LIST.length];
    private TextView title_text;
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private TextView collection_dialog_content;
    private RelativeLayout collection_layout;
    private Button shared_btn;
    private View conrtainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_collectioncontentdialog);
        setFinishOnTouchOutside(false);
        LOCATION = getIntent().getStringExtra("LOCATION");
        helper = new DBHelper(this);
        liteDatabase = helper.getWritableDatabase();

        title_text = (TextView) findViewById(R.id.title_text);
        collection_dialog_content = (TextView) findViewById(R.id.collection_dialog_content);
        collection_layout = (RelativeLayout) findViewById(R.id.collection_layout);
        shared_btn = (Button) findViewById(R.id.shared_btn);
        close_btn = (Button) findViewById(R.id.close_btn);
        for (int i = 0; i < Contact.PUZZLEVIEW_LIST.length; i++) {
            puzzleview[i] = (TextView) findViewById(Contact.PUZZLEVIEW_LIST[i]);
        }

        if (LOCATION.equals("경복궁")) {
            collection_layout.setBackground(getResources().getDrawable(R.drawable.gyeongbokgung_image_converted));
            title_text.setText(getString(R.string.place_one));
            collection_dialog_content.setText(getString(R.string.place_one_content));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[1], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count].setVisibility(View.INVISIBLE);
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (LOCATION.equals("창경궁")) {
            collection_layout.setBackground(getResources().getDrawable(R.drawable.changgyeonggung_image));
            title_text.setText(getString(R.string.place_two));
            collection_dialog_content.setText(getString(R.string.place_two_content));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[2], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count].setVisibility(View.INVISIBLE);
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (LOCATION.equals("덕수궁")) {
            collection_layout.setBackground(getResources().getDrawable(R.drawable.deoksugung_image));
            title_text.setText(getString(R.string.place_three));
            collection_dialog_content.setText(getString(R.string.place_three_content));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count].setVisibility(View.INVISIBLE);
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (LOCATION.equals("창덕궁")) {
            collection_layout.setBackground(getResources().getDrawable(R.drawable.changdeokgung_image));
            title_text.setText(getString(R.string.place_four));
            collection_dialog_content.setText(getString(R.string.place_four_content));
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[4], null, null, null, null, null, null);
            try {
                int count = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count].setVisibility(View.INVISIBLE);
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count].setVisibility(View.VISIBLE);
                    }
                    if (count < 5) {
                        count++;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }

        close_btn.setOnClickListener(this);
        shared_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_btn:
                finish();
                break;
            case R.id.shared_btn:
                Shared();
                finish();
                break;
        }
    }

    protected void Shared() {
        conrtainer = collection_layout;
        conrtainer.buildDrawingCache();
        Bitmap captureView = conrtainer.getDrawingCache();
        FileOutputStream fos;
        String save;
        String dateString = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date currentTime_1 = new Date();
            dateString = formatter.format(currentTime_1);
            File sdCardPath = Environment.getExternalStorageDirectory();
            save = sdCardPath.getAbsolutePath() + "/Pictures/Screenshots/" + dateString + ".png";
            fos = new FileOutputStream(save);
            captureView.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/" + dateString + ".png"));
        Intent shareddintent = new Intent(Intent.ACTION_SEND);

        shareddintent.putExtra(Intent.EXTRA_STREAM, uri);
        shareddintent.setType("image/*");

        startActivity(Intent.createChooser(shareddintent, "공유"));
    }

}
