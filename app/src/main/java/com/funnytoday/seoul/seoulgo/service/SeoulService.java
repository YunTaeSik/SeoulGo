package com.funnytoday.seoul.seoulgo.service;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.main.MainActivity;
import com.funnytoday.seoul.seoulgo.sqlite.DBHelper;
import com.funnytoday.seoul.seoulgo.util.Contact;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by YunTaeSik on 2016-08-24.
 */
public class SeoulService extends Service implements LocationListener {
    private String TAG = "SeoulService";
    private DBHelper helper;
    private SQLiteDatabase liteDatabase;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "servicestart");
        saveDB();
        startLocationService(); //측정 시작
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        // SinhanLocation(latitude, longitude);
        GyeongbokgungLocation(latitude, longitude);
        ChanggyeonggungLocation(latitude, longitude);
        DeoksugungLocation(latitude, longitude);
        ChangdeokgungLocation(latitude, longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void GyeongbokgungLocation(double latitude, double longitude) {
        ArrayList<Integer> insideGyeongbokgung_List = new ArrayList<>();

        Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[1], null, null, null, null, null, null);  //경복궁
        try {
            while (cursor.moveToNext()) {
                int number = Integer.parseInt(cursor.getString(1)); // number
                String puzzle_flag = cursor.getString(2);
                if (puzzle_flag.equals("off")) {
                    if (distance(Contact.Gyeongbokgung_Lat[number], Contact.Gyeongbokgung_Lon[number], latitude, longitude) <= 20) {
                        if (!insideGyeongbokgung_List.contains(number)) { //20m안일떄
                            insideGyeongbokgung_List.add(number);
                        }
                    } else {
                        if (insideGyeongbokgung_List.contains(number)) { // 20m 밖일때
                            insideGyeongbokgung_List.remove(number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        if (insideGyeongbokgung_List.size() > 0) {
            notiFication(insideGyeongbokgung_List.size(), "경복궁");
            Intent notiIntent = new Intent(Contact.NOTI_Gyeongbokgung);
            notiIntent.putIntegerArrayListExtra(Contact.NOTI_Gyeongbokgung_LIST, insideGyeongbokgung_List);
            sendBroadcast(notiIntent);
        } else {
            Intent notiIntent = new Intent(Contact.NOTI_Gyeongbokgung_LIST_OUTSIDE);
            sendBroadcast(notiIntent);
        }
    }

    private void ChanggyeonggungLocation(double latitude, double longitude) {
        ArrayList<Integer> insideChanggyeonggung_List = new ArrayList<>();
        Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[2], null, null, null, null, null, null);  //창경궁
        // Log.e(TAG, String.valueOf(cursor.getCount()));
        try {
            while (cursor.moveToNext()) {
                int number = Integer.parseInt(cursor.getString(1)); // number
                String puzzle_flag = cursor.getString(2);
                if (puzzle_flag.equals("off")) {
              /*  Toast.makeText(getApplicationContext(), "위치=" + Contact.Changgyeonggung[number] +
                        " 거리=" + distance(Contact.Changgyeonggung_Lat[number], Contact.Changgyeonggung_Lon[number], latitude, longitude), Toast.LENGTH_SHORT).show();*/
                    Log.e(TAG, "위치=" + Contact.Changgyeonggung[number] +
                            " 거리=" + distance(Contact.Changgyeonggung_Lat[number], Contact.Changgyeonggung_Lon[number], latitude, longitude));
                    if (distance(Contact.Changgyeonggung_Lat[number], Contact.Changgyeonggung_Lon[number], latitude, longitude) <= 20) {
                        if (!insideChanggyeonggung_List.contains(number)) { //20m안일떄
                            insideChanggyeonggung_List.add(number);
                        }
                    } else {
                        if (insideChanggyeonggung_List.contains(number)) { // 20m 밖일때
                            insideChanggyeonggung_List.remove(number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        if (insideChanggyeonggung_List.size() > 0) {
            notiFication(insideChanggyeonggung_List.size(), "창경궁");
            Intent notiIntent = new Intent(Contact.NOTI_Changgyeonggung);
            notiIntent.putIntegerArrayListExtra(Contact.NOTI_Changgyeonggung_LIST, insideChanggyeonggung_List);
            sendBroadcast(notiIntent);
        } else {
            Intent notiIntent = new Intent(Contact.NOTI_Changgyeonggung_LIST_OUTSIDE);
            sendBroadcast(notiIntent);
        }
    }

    private void DeoksugungLocation(double latitude, double longitude) {
        ArrayList<Integer> insideDeoksugung_List = new ArrayList<>();
        Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[3], null, null, null, null, null, null);  //덕수궁
        //   Log.e(TAG, String.valueOf(cursor.getCount()));
        try {
            while (cursor.moveToNext()) {
                int number = Integer.parseInt(cursor.getString(1)); // number
                String puzzle_flag = cursor.getString(2);
                if (puzzle_flag.equals("off")) {
               /* Toast.makeText(getApplicationContext(), "위치=" + Contact.Deoksugung[number] +
                        " 거리=" + distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude), Toast.LENGTH_SHORT).show();*/
                    Log.e(TAG, "위치=" + Contact.Deoksugung[number] +
                            " 거리=" + distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude));
                    if (distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude) <= 20) {
                        if (!insideDeoksugung_List.contains(number)) { //20m안일떄
                            insideDeoksugung_List.add(number);
                        }
                    } else {
                        if (insideDeoksugung_List.contains(number)) { // 20m 밖일때
                            insideDeoksugung_List.remove(number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        if (insideDeoksugung_List.size() > 0) {
            notiFication(insideDeoksugung_List.size(), "덕수궁");
            Intent notiIntent = new Intent(Contact.NOTI_Deoksugung);
            notiIntent.putIntegerArrayListExtra(Contact.NOTI_Deoksugung_LIST, insideDeoksugung_List);
            sendBroadcast(notiIntent);
        } else {
            Intent notiIntent = new Intent(Contact.NOTI_Deoksugung_LIST_OUTSIDE);
            sendBroadcast(notiIntent);
        }
    }

    private void ChangdeokgungLocation(double latitude, double longitude) {
        ArrayList<Integer> insideChangdeokgung_List = new ArrayList<>();
        Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[4], null, null, null, null, null, null);  //덕수궁
        //   Log.e(TAG, String.valueOf(cursor.getCount()));
        try {
            while (cursor.moveToNext()) {
                int number = Integer.parseInt(cursor.getString(1)); // number
                String puzzle_flag = cursor.getString(2);
                if (puzzle_flag.equals("off")) {
               /* Toast.makeText(getApplicationContext(), "위치=" + Contact.Deoksugung[number] +
                        " 거리=" + distance(Contact.Deoksugung_Lat[number], Contact.Deoksugung_Lon[number], latitude, longitude), Toast.LENGTH_SHORT).show();*/
                    Log.e(TAG, "위치=" + Contact.Changdeokgung[number] +
                            " 거리=" + distance(Contact.Changdeokgung_Lat[number], Contact.Changdeokgung_Lon[number], latitude, longitude));
                    if (distance(Contact.Changdeokgung_Lat[number], Contact.Changdeokgung_Lon[number], latitude, longitude) <= 20) {
                        if (!insideChangdeokgung_List.contains(number)) { //20m안일떄
                            insideChangdeokgung_List.add(number);
                        }
                    } else {
                        if (insideChangdeokgung_List.contains(number)) { // 20m 밖일때
                            insideChangdeokgung_List.remove(number);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        if (insideChangdeokgung_List.size() > 0) {
            notiFication(insideChangdeokgung_List.size(), "창덕궁");
            Intent notiIntent = new Intent(Contact.NOTI_Changdeokgung);
            notiIntent.putIntegerArrayListExtra(Contact.NOTI_Changdeokgung_LIST, insideChangdeokgung_List);
            sendBroadcast(notiIntent);
        } else {
            Intent notiIntent = new Intent(Contact.NOTI_Changdeokgung_LIST_OUTSIDE);
            sendBroadcast(notiIntent);
        }
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2) {  //거리 구하기
        double EARTH_R, Rad, radLat1, radLat2, radDist;
        double distance, ret;
        EARTH_R = 6371000.0;
        Rad = Math.PI / 180;
        radLat1 = Rad * lat1;
        radLat2 = Rad * lat2;
        radDist = Rad * (lon1 - lon2);
        distance = Math.sin(radLat1) * Math.sin(radLat2);
        distance = distance + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radDist);
        ret = EARTH_R * Math.acos(distance);
        double rslt = Math.round(ret);
        return rslt;
    }

    private void notiFication(int size, String Location) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("LOCATION", Location);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setSmallIcon(R.drawable.gyeongbokgung_icon_image);
        mBuilder.setTicker(getString(R.string.app_name));
        mBuilder.setWhen(System.currentTimeMillis());
        mBuilder.setNumber(size);
        mBuilder.setContentTitle(getString(R.string.noti_tilte));
        mBuilder.setContentText(getText(R.string.noti_text));
        mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            nm.notify(111, mBuilder.build());
        }
    }

    private void saveDB() {
        helper = new DBHelper(this);
        liteDatabase = helper.getWritableDatabase();
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int k = 0; k < Contact.USER_TABLE_NAME.length; k++) {
            Cursor cursor = liteDatabase.query(Contact.USER_TABLE_NAME[k], null, null, null, null, null, null);
            if (cursor.getCount() < 6) {
                for (int i = 0; i < 6; i++) {
                    randomNumber(numberList);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("number", numberList.get(i));
                    Log.e("number", String.valueOf(numberList.get(i)));
                    contentValues.put("puzzle", "off");
                    liteDatabase.insert(Contact.USER_TABLE_NAME[k], null, contentValues);
                    if (cursor.getCount() == 6) {
                        break;
                    }
                }
            }
        }
    }

    private void randomNumber(ArrayList<Integer> numberList) {
        Random random = new Random();
        while (numberList.size() <= 5) {
            int number = random.nextInt(15);
            if (!(numberList.contains(number))) {
                numberList.add(number);
            }
        }
    }
}
