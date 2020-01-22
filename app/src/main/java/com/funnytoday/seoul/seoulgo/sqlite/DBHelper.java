package com.funnytoday.seoul.seoulgo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.funnytoday.seoul.seoulgo.util.Contact;

/**
 * Created by YunTaeSik on 2016-08-25.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (int i = 0; i < Contact.USER_TABLE_NAME.length; i++) {
            db.execSQL("CREATE TABLE if not exists " + Contact.USER_TABLE_NAME[i]
                    + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT not null, puzzle TEXT not null);");
        }
        db.execSQL("CREATE TABLE if not exists " + Contact.COUPON
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, place TEXT not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < Contact.USER_TABLE_NAME.length; i++) {
            db.execSQL("CREATE TABLE if not exists " + Contact.USER_TABLE_NAME[i]
                    + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT not null, puzzle TEXT not null);");
        }
        db.execSQL("CREATE TABLE if not exists " + Contact.COUPON
                + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, place TEXT not null);");
    }
}
