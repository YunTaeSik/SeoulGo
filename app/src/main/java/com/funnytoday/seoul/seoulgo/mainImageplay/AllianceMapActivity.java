package com.funnytoday.seoul.seoulgo.mainImageplay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.util.Contact;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Note on 2016-10-29.
 */
public class AllianceMapActivity extends FragmentActivity implements OnMapReadyCallback {
    private String location;
    private int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_one_map);
        location = getIntent().getStringExtra("tag"); // 경복궁 , 창경궁, 덕수궁
        position = getIntent().getIntExtra("position", 0);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap map) {
        if (location.equals("경복궁")) {
            LatLng address = new LatLng(Contact.Gyeongbokgung_Alliance_Lat[position], Contact.Gyeongbokgung_Alliance_Lon[position]);
            map.addMarker(new MarkerOptions().position(address).title(getResources().getStringArray(R.array.gyeongbokgung_store)[position]));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("창경궁")) {
            LatLng address = new LatLng(Contact.Changgyeonggung_Alliance_Lat[position], Contact.Changgyeonggung_Alliance_Lon[position]);
            map.addMarker(new MarkerOptions().position(address).title(getResources().getStringArray(R.array.gyeongbokgung_store)[position]));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("덕수궁")) {
            LatLng address = new LatLng(Contact.Deoksugung_Alliance_Lat[position], Contact.Deoksugung_Alliance_Lon[position]);
            map.addMarker(new MarkerOptions().position(address).title(getResources().getStringArray(R.array.deoksugung_store)[position]));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        }
    }
}