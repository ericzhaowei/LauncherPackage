package com.ider.launcherpackage.views;

import android.content.Context;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.ider.launcherpackage.launcher.AppAdapter;
import com.ider.launcherpackage.launcher.ItemEntry;
import com.ider.launcherpackage.R;
import com.ider.launcherpackage.common.ApplicationUtil;

import java.util.ArrayList;


public class AppSelectWindow implements View.OnKeyListener, AdapterView.OnItemClickListener{

    private Context mContext;
    private ArrayList<ItemEntry> allApps;
    private ShortcutView baseView;
    private PopupWindow popWindow;
    private static AppSelectWindow INSTANCE;

    private AppSelectWindow(Context context) {
        this.mContext = context;
    }

    public static AppSelectWindow getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new AppSelectWindow(context);
        }
        return INSTANCE;
    }


    public void showAppPopWindow(ShortcutView baseView) {
        this.baseView = baseView;
        View view = View.inflate(mContext, R.layout.app_select_window, null);
        view.setOnKeyListener(this);

        GridView gridView = (GridView) view.findViewById(R.id.app_select_grid);
        gridView.setOnKeyListener(this);
        gridView.setOnItemClickListener(this);
        setupAppGrid(gridView);

        this.popWindow = new PopupWindow(view, -1, -1, true);
        this.popWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        this.popWindow.showAtLocation(baseView, Gravity.CENTER, 0, 0);
    }


    public void setupAppGrid(GridView gridView) {
        allApps = ApplicationUtil.queryApplication(mContext);
        AppAdapter adapter = new AppAdapter(mContext, R.layout.app_select_item, allApps);
        gridView.setAdapter(adapter);
    }



    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            this.popWindow.dismiss();
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        baseView.mItemEntry = allApps.get(i);
        baseView.updateSelf();
        baseView.save();
        popWindow.dismiss();
    }
}
