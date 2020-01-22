package com.funnytoday.seoul.seoulgo.animation;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by 박상돈 on 2016-09-06.
 */
public class HomeActivitySetting {
    public static void HelpMenuOn(final LinearLayout linearLayout, String flag) {
        linearLayout.setVisibility(View.VISIBLE);
        }
    public static void HelpMenuOff(final LinearLayout linearLayout, String flag) {
        linearLayout.setVisibility(View.GONE);
    }
}

