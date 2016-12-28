package com.ider.launcherpackage.common;

import android.graphics.Bitmap;

import com.ider.launcherpackage.R;
import com.ider.launcherpackage.launcher.LauncherApplication;

/**
 * Created by ider-eric on 2016/12/28.
 */

public class EntryImageGetter {



//
//    public Bitmap getEntryImage() {
//
//    }


    public static Bitmap getDefaultImage() {
        return BitmapTools.getInstance().getResourcecBitmap(LauncherApplication.getContext(), R.mipmap.add_item_white);
    }

}
