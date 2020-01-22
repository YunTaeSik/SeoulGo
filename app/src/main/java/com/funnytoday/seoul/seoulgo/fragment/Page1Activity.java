package com.funnytoday.seoul.seoulgo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.funnytoday.seoul.seoulgo.R;
import com.funnytoday.seoul.seoulgo.adapter.CollectionAdapter;
import com.funnytoday.seoul.seoulgo.util.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/* Page1Activity.java */
public class Page1Activity extends Fragment {
    // private ViewPager collection_list_view;
    private GridView collection_list_view;
    private CollectionAdapter collectionAdapter;
    private LinearLayout shared_latout;
    private View conrtainer;
    private RelativeLayout collection_progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_page1, container, false);
        collection_list_view = (GridView) layout.findViewById(R.id.collection_list_view);
        shared_latout = (LinearLayout) layout.findViewById(R.id.shared_latout);
        collection_progress = (RelativeLayout) layout.findViewById(R.id.collection_progress);
        collectionAdapter = new CollectionAdapter(getContext());
        collection_list_view.setAdapter(collectionAdapter);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contact.COLLECTION_FINISH);
        intentFilter.addAction(Contact.SHARED_CLICK);
        getContext().registerReceiver(broadcastReceiver, intentFilter);

        return layout;
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Contact.COLLECTION_FINISH)) {
                collection_progress.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {// 1 초 후에 실행
                    @Override
                    public void run() {
                        collection_progress.setVisibility(View.GONE);
                    }
                }, 1000);
                collectionAdapter.notifyDataSetChanged();
            } else if (intent.getAction().equals(Contact.SHARED_CLICK)) {
                Shared();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(broadcastReceiver);
    }


    protected void Shared() {
        conrtainer = collection_list_view;
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

        // Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/capture.png"));
        Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/Screenshots/" + dateString + ".png"));
        Intent shareddintent = new Intent(Intent.ACTION_SEND);

        shareddintent.putExtra(Intent.EXTRA_STREAM, uri);

        shareddintent.setType("image/*");

        startActivity(Intent.createChooser(shareddintent, "공유"));
    }

}
