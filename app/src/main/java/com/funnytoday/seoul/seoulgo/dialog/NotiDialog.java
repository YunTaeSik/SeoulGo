package com.funnytoday.seoul.seoulgo.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.funnytoday.seoul.seoulgo.R;

/**
 * Created by YunTaeSik on 2016-08-24.
 */
public class NotiDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_dialog);
        setFinishOnTouchOutside(false);
    }
}
