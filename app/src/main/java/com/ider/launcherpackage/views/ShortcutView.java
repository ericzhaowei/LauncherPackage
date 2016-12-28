package com.ider.launcherpackage.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.ider.launcherpackage.common.EntryImageGetter;
import com.ider.launcherpackage.launcher.ItemEntry;
import com.ider.launcherpackage.launcher.LauncherApplication;
import com.ider.launcherpackage.R;
import com.ider.launcherpackage.common.PreferenceManager;


public class ShortcutView extends BaseEntryView {

    private static final String TAG = "ShortcutView";

    public ItemEntry mItemEntry;
    public AppSelectWindow appSelectWindow;
    public ShortcutEditWindow editWindow;
    public PreferenceManager preferenceManager;
    private ImageView shortcutImage;
    private TextView shortcutText;


    public ShortcutView(Context context) {
        this(context, null);
    }

    public ShortcutView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        LayoutInflater.from(context).inflate(R.layout.default_shortcut, this);
        shortcutImage = (ImageView) findViewById(R.id.shortcut_image);
        shortcutText = (TextView) findViewById(R.id.shortcut_text);
        preferenceManager = PreferenceManager.getInstance(LauncherApplication.getContext());
        String savedPackage = preferenceManager.getString((String) getTag());
        if(savedPackage != null) {
            this.mItemEntry = new ItemEntry(savedPackage);
        } else {
            setDefault();
        }
    }

    @Override
    public void setDefault() {
        // 设置默认的应用
        String tag = (String) getTag();
        String defaultPackage = null;
        switch (tag) {
            case "10":
                defaultPackage = "com.android.settings";
                break;
            case "11":
                defaultPackage = "com.dangbeimarket";
                break;
        }
        if(defaultPackage != null) {
            this.mItemEntry = new ItemEntry(defaultPackage);
        }
    }

    @Override
    public void setBitmap() {
        if(this.mItemEntry == null) {
            Bitmap mBitmap = this.mBitmapTools.getDefaultShortcutBitmap(getContext(),
                    R.layout.default_shortcut,
                    getWidth(),
                    getHeight(),
                    R.drawable.default_background);
            setImageBitmap(mBitmap);
        } else {
            Bitmap mBitmap = this.mBitmapTools.getShortcutBitmap(getContext(),
                    R.layout.default_shortcut,
                    R.id.shortcut_image,
                    R.id.shortcut_text,
                    ItemEntry.loadImage(getContext(), this.mItemEntry.getPackageName()),
                    ItemEntry.loadLabel(getContext(), this.mItemEntry.getPackageName()),
                    getWidth(),
                    getHeight(),
                    R.drawable.default_background);
            setImageBitmap(mBitmap);
        }
    }


    @Override
    public void updateSelf() {
        setBitmap();
//        if (mItemEntry != null) {
//            shortcutImage.setImageBitmap();
//            shortcutText.setText(ItemEntry.loadLabel(getContext(), mItemEntry.getPackageName()));
//        } else {
//            // 加号
//            shortcutImage.setImageBitmap(EntryImageGetter.getDefaultImage());
//            shortcutText.setText(R.string.shortcut_default_title);
//        }
    }


    @Override
    public void onClick() {
        if(this.mItemEntry == null) {
            showAppSelectWindow();
        } else {
            String mPackageName = this.mItemEntry.getPackageName();
            if(mPackageName != null) {
                Intent intent = mContext.getPackageManager().getLaunchIntentForPackage(mPackageName);
                if(intent != null) {
                    mContext.startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onLongClick() {
        showEditWindow();
    }

    public void save() {
        preferenceManager.putString((String) getTag(), mItemEntry.getPackageName());
    }
    public void remove() {
        preferenceManager.remove((String) getTag());
    }


    public void showEditWindow() {
        if(this.editWindow == null) {
            editWindow = ShortcutEditWindow.getInstance(LauncherApplication.getContext());
        }
        this.editWindow.showEditWindow(this);
    }


    public void showAppSelectWindow() {
        if(this.appSelectWindow == null) {
            this.appSelectWindow = AppSelectWindow.getInstance(LauncherApplication.getContext());
        }
        this.appSelectWindow.showAppPopWindow(this);
    }

}