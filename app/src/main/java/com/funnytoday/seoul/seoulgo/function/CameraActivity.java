package com.funnytoday.seoul.seoulgo.function;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.MiniMapAdapter;
import com.funnytoday.seoul.seoulgo.dialog.CollectionFinishDialog;
import com.funnytoday.seoul.seoulgo.dialog.GpsDialog;
import com.funnytoday.seoul.seoulgo.dialog.HintDialog;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by YunTaeSik on 2016-10-24.
 */
public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback, View.OnClickListener {
    private String TAG = "CameraActivity";
    private String location;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera mCamera;
    private TextView puzzle_item;
    private RelativeLayout collection_layout;
    private RelativeLayout puzzle_item_layout;
    private Animation puzzle_anim;
    private Animation puzzle_click_anim;
    private Animation collection_layout_anim;
    private String Location_Tag = "";
    private String Location_Number = "0";

    private DBHelper helper;
    private SQLiteDatabase liteDatabase;
    private ImageView[] puzzleview = new ImageView[Contact.PUZZLEVIEW_LIST.length];
    private TextView puzzle_name;
    private TextView puzzle_content;
    private TextView hint_layout;
    private TextView coupon_layout;

    private RelativeLayout map_layout;
    private Button map_btn;
    private String maptag = "gone";
    private Animation map_animimation;
    private Animation map_animimation_down;
    private LinearLayout all_minimap_layout;
    private ImageView mini_image;
    private GridView minimap_grid;
    private GridView minimap_grid_two;
    private MiniMapAdapter miniMapAdapter;

    private String content_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        location = getIntent().getStringExtra("LOCATION");
        helper = new DBHelper(this);
        liteDatabase = helper.getWritableDatabase();

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        puzzle_item = (TextView) findViewById(R.id.puzzle_item);
        collection_layout = (RelativeLayout) findViewById(R.id.collection_layout);
        puzzle_item_layout = (RelativeLayout) findViewById(R.id.puzzle_item_layout);
        puzzle_name = (TextView) findViewById(R.id.puzzle_name);
        puzzle_content = (TextView) findViewById(R.id.puzzle_content);
        hint_layout = (TextView) findViewById(R.id.hint_layout);
        coupon_layout = (TextView) findViewById(R.id.coupon_layout);
        map_layout = (RelativeLayout) findViewById(R.id.map_layout);
        map_btn = (Button) findViewById(R.id.map_btn);
        all_minimap_layout = (LinearLayout) findViewById(R.id.all_minimap_layout);
        mini_image = (ImageView) findViewById(R.id.mini_image);
        minimap_grid = (GridView) findViewById(R.id.minimap_grid);
        minimap_grid_two = (GridView) findViewById(R.id.minimap_grid_two);


        map_btn.setOnClickListener(this);

        for (int i = 0; i < Contact.PUZZLEVIEW_LIST.length; i++) {
            puzzleview[i] = (ImageView) findViewById(Contact.PUZZLEVIEW_LIST[i]);
            Picasso.with(this).load(R.drawable.puzzle_hide_background).fit().into(puzzleview[i]);
        }

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        puzzle_anim = AnimationUtils.loadAnimation(this, R.anim.puzzle_animation);
        puzzle_click_anim = AnimationUtils.loadAnimation(this, R.anim.puzzle_click_animation);
        collection_layout_anim = AnimationUtils.loadAnimation(this, R.anim.collection_layout_animation);
        map_animimation = AnimationUtils.loadAnimation(this, R.anim.minimap_animation_up);
        map_animimation_down = AnimationUtils.loadAnimation(this, R.anim.minimap_animation_down);
        collection_layout.startAnimation(collection_layout_anim);
        permissionCheck();
        setBroadcastReceiver();
        CheckGps();
        setPuzzleView();
        if (location.equals("경복궁")) {
            puzzle_name.setTextColor(getResources().getColor(R.color.camera_text_color));
            puzzle_content.setTextColor(getResources().getColor(R.color.camera_text_color));
            puzzle_name.setText(getString(R.string.place_one));
            puzzle_content.setText(getString(R.string.place_one_content));
            collection_layout.setBackground(getResources().getDrawable(R.drawable.gyeongbokgung_image_converted));
            Picasso.with(this).load(R.drawable.gb_minimap_image).fit().into(mini_image);
            miniMapAdapter = new MiniMapAdapter(getApplicationContext(), location);
            minimap_grid.setAdapter(miniMapAdapter);
            // minimap_grid.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        } else if (location.equals("창경궁")) {
            puzzle_name.setText(getString(R.string.place_two));
            puzzle_content.setText(getString(R.string.place_two_content));
            collection_layout.setBackground(getResources().getDrawable(R.drawable.changgyeonggung_image));
            Picasso.with(this).load(R.drawable.cg_minimap_image).fit().into(mini_image);
            miniMapAdapter = new MiniMapAdapter(getApplicationContext(), location);
            minimap_grid.setAdapter(miniMapAdapter);
        } else if (location.equals("덕수궁")) {
            puzzle_name.setText(getString(R.string.place_three));
            puzzle_content.setText(getString(R.string.place_three_content));
            collection_layout.setBackground(getResources().getDrawable(R.drawable.deoksugung_image));
            Picasso.with(this).load(R.drawable.de_minimap_image).fit().into(mini_image);
            miniMapAdapter = new MiniMapAdapter(getApplicationContext(), location);
            minimap_grid_two.setAdapter(miniMapAdapter);
        } else if (location.equals("창덕궁")) {
            puzzle_name.setTextColor(getResources().getColor(R.color.camera_text_color));
            puzzle_content.setTextColor(getResources().getColor(R.color.camera_text_color));
            puzzle_name.setText(getString(R.string.place_four));
            puzzle_content.setText(getString(R.string.place_four_content));
            collection_layout.setBackground(getResources().getDrawable(R.drawable.changdeokgung_image));
            Picasso.with(this).load(R.drawable.cd_minimap_image).fit().into(mini_image);
            miniMapAdapter = new MiniMapAdapter(getApplicationContext(), location);
            minimap_grid_two.setAdapter(miniMapAdapter);
        }

        puzzle_item.setOnClickListener(this);
        hint_layout.setOnClickListener(this);
        coupon_layout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void OpenCamera() {
        try {
            mCamera = Camera.open();
            mCamera.setPreviewDisplay(surfaceHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Camera.Parameters parameters = mCamera.getParameters();
            List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
            Camera.Size cs = previewSizes.get(0);
            parameters.setRotation(90);
            parameters.setPreviewSize(cs.width, cs.height);
            mCamera.setDisplayOrientation(90);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CloseCamera() {
        try {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void permissionCheck() {
        int permissionCheck_Location = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permissionCheck_Camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permissionCheck_Camera == PackageManager.PERMISSION_DENIED || permissionCheck_Location == PackageManager.PERMISSION_DENIED) { //권한 없음
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA}, 1);
            }
        }
    }

    private void setBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contact.NOTI_Gyeongbokgung);
        intentFilter.addAction(Contact.NOTI_Changgyeonggung);
        intentFilter.addAction(Contact.NOTI_Deoksugung);
        intentFilter.addAction(Contact.NOTI_Changdeokgung);
        intentFilter.addAction(Contact.NOTI_Gyeongbokgung_LIST_OUTSIDE);
        intentFilter.addAction(Contact.NOTI_Changgyeonggung_LIST_OUTSIDE);
        intentFilter.addAction(Contact.NOTI_Deoksugung_LIST_OUTSIDE);
        intentFilter.addAction(Contact.NOTI_Changdeokgung_LIST_OUTSIDE);
        intentFilter.addAction(Contact.COLLECTION_GO);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (location.equals("경복궁")) {
                if (intent.getAction().equals(Contact.NOTI_Gyeongbokgung)) {
                    puzzle_item_layout.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    puzzle_item.setBackgroundDrawable(getResources().getDrawable(Contact.PUZZLE_ITEM_IMAGE[random.nextInt(2)]));
                    puzzle_item.startAnimation(puzzle_anim);
                    ArrayList<Integer> GyeongbokgungList = intent.getIntegerArrayListExtra(Contact.NOTI_Gyeongbokgung_LIST);
                    Location_Tag = Contact.USER_TABLE_NAME[1]; //경복궁
                    Location_Number = String.valueOf(GyeongbokgungList.get(0)); //해당 넘버
                    puzzle_item.setText((getResources().getStringArray(R.array.gyeongbokgung_location)[GyeongbokgungList.get(0)]));
                    content_Text = (getResources().getStringArray(R.array.gyeongbokgung_location_explain)[GyeongbokgungList.get(0)]);
                } else if (intent.getAction().equals(Contact.NOTI_Gyeongbokgung_LIST_OUTSIDE)) {
                    puzzle_item_layout.setVisibility(View.GONE);
                }
            } else if (location.equals("창경궁")) {
                if (intent.getAction().equals(Contact.NOTI_Changgyeonggung)) {
                    puzzle_item_layout.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    puzzle_item.setBackgroundDrawable(getResources().getDrawable(Contact.PUZZLE_ITEM_IMAGE[random.nextInt(2)]));
                    puzzle_item.startAnimation(puzzle_anim);
                    ArrayList<Integer> ChanggyeonggungList = intent.getIntegerArrayListExtra(Contact.NOTI_Changgyeonggung_LIST);
                    Location_Tag = Contact.USER_TABLE_NAME[2]; //창경궁
                    Location_Number = String.valueOf(ChanggyeonggungList.get(0)); //해당 넘버
                    // puzzle_item.setText(Contact.Changgyeonggung[ChanggyeonggungList.get(0)]);
                    puzzle_item.setText((getResources().getStringArray(R.array.changgyeonggung_location)[ChanggyeonggungList.get(0)]));
                    content_Text = getResources().getStringArray(R.array.changgyeonggung_location_explain)[ChanggyeonggungList.get(0)];
                } else if (intent.getAction().equals(Contact.NOTI_Changgyeonggung_LIST_OUTSIDE)) {
                    puzzle_item_layout.setVisibility(View.GONE);
                }
            } else if (location.equals("덕수궁")) {
                if (intent.getAction().equals(Contact.NOTI_Deoksugung)) {
                    puzzle_item_layout.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    puzzle_item.setBackgroundDrawable(getResources().getDrawable(Contact.PUZZLE_ITEM_IMAGE[random.nextInt(2)]));
                    puzzle_item.startAnimation(puzzle_anim);
                    ArrayList<Integer> DeoksugungList = intent.getIntegerArrayListExtra(Contact.NOTI_Deoksugung_LIST);
                    Location_Tag = Contact.USER_TABLE_NAME[3]; //덕수궁
                    Location_Number = String.valueOf(DeoksugungList.get(0)); //해당 넘버
                    // puzzle_item.setText(Contact.Deoksugung[DeoksugungList.get(0)]);
                    puzzle_item.setText((getResources().getStringArray(R.array.deoksugung_location)[DeoksugungList.get(0)]));
                    content_Text = getResources().getStringArray(R.array.deoksugung_location_explain)[DeoksugungList.get(0)];
                } else if (intent.getAction().equals(Contact.NOTI_Deoksugung_LIST_OUTSIDE)) {
                    puzzle_item_layout.setVisibility(View.GONE);
                }
            } else if (location.equals("창덕궁")) {
                if (intent.getAction().equals(Contact.NOTI_Changdeokgung)) {
                    puzzle_item_layout.setVisibility(View.VISIBLE);
                    Random random = new Random();
                    puzzle_item.setBackgroundDrawable(getResources().getDrawable(Contact.PUZZLE_ITEM_IMAGE[random.nextInt(2)]));
                    puzzle_item.startAnimation(puzzle_anim);
                    ArrayList<Integer> ChangdeokgungList = intent.getIntegerArrayListExtra(Contact.NOTI_Changdeokgung_LIST);
                    Location_Tag = Contact.USER_TABLE_NAME[4]; //창덕궁
                    Location_Number = String.valueOf(ChangdeokgungList.get(0)); //해당 넘버
                    puzzle_item.setText((getResources().getStringArray(R.array.changdeokgung_location)[ChangdeokgungList.get(0)]));
                    content_Text = getResources().getStringArray(R.array.changdeokgung_location_explain)[ChangdeokgungList.get(0)];
                } else if (intent.getAction().equals(Contact.NOTI_Changdeokgung_LIST_OUTSIDE)) {
                    puzzle_item_layout.setVisibility(View.GONE);
                }
            }
            if (intent.getAction().equals(Contact.COLLECTION_GO)) {
                finish();
            }
        }
    };

    private void setPuzzleView() {
        int count = 0;
        if (location.equals("경복궁")) {
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[1], null, null, null, null, null, null);
            try {
                int count2 = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count2].setVisibility(View.INVISIBLE);
                        count++;
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count2].setVisibility(View.VISIBLE);
                    }
                    if (count2 < 5) {
                        count2++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (location.equals("창경궁")) {
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[2], null, null, null, null, null, null);
            try {
                int count2 = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count2].setVisibility(View.INVISIBLE);
                        count++;
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count2].setVisibility(View.VISIBLE);
                    }
                    if (count2 < 5) {
                        count2++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (location.equals("덕수궁")) {
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);
            try {
                int count2 = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count2].setVisibility(View.INVISIBLE);
                        count++;
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count2].setVisibility(View.VISIBLE);
                    }
                    if (count2 < 5) {
                        count2++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        } else if (location.equals("창덕궁")) {
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[4], null, null, null, null, null, null);
            try {
                int count2 = 0;
                while (cursor.moveToNext()) {
                    if (cursor.getString(2).equals("on")) {
                        puzzleview[count2].setVisibility(View.INVISIBLE);
                        count++;
                    } else if (cursor.getString(2).equals("off")) {
                        puzzleview[count2].setVisibility(View.VISIBLE);
                    }
                    if (count2 < 5) {
                        count2++;
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
            } finally {
                cursor.close();
            }
        }
        if (count >= 6) {
            //Toast.makeText(getApplicationContext(), "Finish", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CollectionFinishDialog.class));
        }
    }

    private void setPuzzleContent() {
        puzzle_name.setText(puzzle_item.getText().toString());
        puzzle_content.setText(content_Text);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        recreate();
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.permit_text), Toast.LENGTH_SHORT).show();
                    }
                }
                return;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        OpenCamera();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        CloseCamera();
    }

    private void CheckGps() {
        String gps = android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!(gps.matches(".*gps.*") && gps.matches(".*network.*"))) {
            Intent gpsdialog = new Intent(this, GpsDialog.class);
            startActivity(gpsdialog);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.puzzle_item:
                ContentValues contentValues = new ContentValues();
                contentValues.put("puzzle", "on");
                liteDatabase.update(Location_Tag, contentValues, "number=?", new String[]{Location_Number});    //
                puzzle_item_layout.startAnimation(puzzle_click_anim);
                puzzle_item_layout.setVisibility(View.GONE);
                setPuzzleView();
                setPuzzleContent();
                sendBroadcast(new Intent(Contact.COLLECTION_FINISH));
                break;
            case R.id.hint_layout:
                Intent hint = new Intent(this, HintDialog.class);
                hint.putExtra("LOCATION", location);
                startActivity(hint);
                break;
            case R.id.coupon_layout:
               /* Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("COLLECTION", 1);COLLECTION_GO
                startActivity(intent);*/
                finish();
                sendBroadcast(new Intent(Contact.COLLECTION_GO));
                break;
            case R.id.map_btn:
                if (maptag.equals("gone")) {
                    map_layout.setVisibility(View.VISIBLE);
                    all_minimap_layout.startAnimation(map_animimation);
                    maptag = "visible";
                } else if (maptag.equals("visible")) {
                    all_minimap_layout.startAnimation(map_animimation_down);
                    map_layout.setVisibility(View.GONE);
                    maptag = "gone";
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (maptag.equals("visible")) {
            all_minimap_layout.startAnimation(map_animimation_down);
            map_layout.setVisibility(View.GONE);
            maptag = "gone";
        } else {
            super.onBackPressed();
        }
    }
}
