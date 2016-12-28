package com.ider.launcherpackage.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ider.launcherpackage.R;

import java.util.ArrayList;

/**
 * Created by ider-eric on 2016/12/26.
 */

public class AppAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutId = R.layout.app_select_item;
    private ArrayList<ItemEntry> data;

    public AppAdapter(Context mContext, ArrayList<ItemEntry> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public AppAdapter(Context mContext, int layoutId, ArrayList<ItemEntry> data) {
        this(mContext, data);
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
            holder.image = (ImageView) view.findViewById(R.id.app_item_image);
            holder.text = (TextView) view.findViewById(R.id.app_item_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        ItemEntry entry = data.get(i);
        holder.image.setImageDrawable(ItemEntry.loadImage(mContext, entry.getPackageName()));
        holder.text.setText(ItemEntry.loadLabel(mContext, entry.getPackageName()));

        return view;
    }



    class ViewHolder {
        private ImageView image;
        private TextView text;
    }
}
