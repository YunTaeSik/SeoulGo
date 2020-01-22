package com.funnytoday.seoul.seoulgo.mainImageplay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.funnytoday.seoul.seoulgo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by 박상돈 on 2016-09-09.
 */
public class ImageOneMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private String location;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_one_map);
        location = getIntent().getStringExtra("LOCATION"); // 경복궁 , 창경궁, 덕수궁, 창덕궁
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map) {
        if (location.equals("경복궁")) {
            LatLng address = new LatLng(37.5760645, 126.9770063);
            map.addMarker(new MarkerOptions().position(address).title("여기가 경복궁 입니다!").snippet("서울특별시 종로구 사직로 161"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("창경궁")) {
            LatLng address = new LatLng(37.583330, 126.994869);
            map.addMarker(new MarkerOptions().position(address).title("여기가 창경궁 입니다!").snippet("서울특별시 종로구 창경궁로 185"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("덕수궁")) {
            LatLng address = new LatLng(37.565850, 126.975143);
            map.addMarker(new MarkerOptions().position(address).title("여기가 덕수궁 입니다!").snippet("서울특별시 중구 세종대로 99"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("창덕궁")) {
            LatLng address = new LatLng(37.579414, 126.991086);
            map.addMarker(new MarkerOptions().position(address).title("여기가 창덕궁 입니다!").snippet("서울특별시 중구 세종대로 99"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        }
    }
}
