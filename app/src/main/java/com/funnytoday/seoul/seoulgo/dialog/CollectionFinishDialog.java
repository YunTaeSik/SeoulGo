package com.funnytoday.seoul.seoulgo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.util.Contact;

/**
 * Created by sky87 on 2016-10-25.
 */
public class CollectionFinishDialog extends Activity implements View.OnClickListener {
    private Button ok_btn;
    private Button cancle_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.activity_dialog);
        setFinishOnTouchOutside(false);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        cancle_btn = (Button) findViewById(R.id.cancle_btn);
        ok_btn.setOnClickListener(this);
        cancle_btn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_btn:
                finish();
               sendBroadcast(new Intent(Contact.COLLECTION_GO));
                break;
            case R.id.cancle_btn:
                finish();
                break;
        }

    }
}
