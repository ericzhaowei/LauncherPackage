package com.ider.launcherpackage.common;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ider.launcherpackage.R;

public class BitmapTools {

    private static BitmapTools INSTANCE;
    private BitmapTools() {

    }

    public static BitmapTools getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BitmapTools();
        }
        return INSTANCE;
    }

    public Bitmap getResourcecBitmap(Context mContext, int resourceId) {
        Drawable drawable = mContext.getResources().getDrawable(resourceId);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        return bitmapDrawable.getBitmap();
    }


    public Bitmap getDefaultShortcutBitmap(Context mContext,
                                           int layoutId,
                                           int itemWidth,
                                           int itemHeight,
                                           int backgroundDrawable) {

        View root = View.inflate(mContext, layoutId, null);
        root.measure(MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY));
        root.layout(0, 0, root.getMeasuredWidth(), root.getMeasuredHeight());
        root.setBackgroundResource(backgroundDrawable);

        Bitmap bitmap = Bitmap.createBitmap(itemWidth, itemHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        root.draw(canvas);
        canvas.restore();
        return bitmap;

    }


    public Bitmap getShortcutBitmap(Context context,
                                    int layoutId,
                                    int imageId,
                                    int textId,
                                    Drawable imageDrawable,
                                    String title,
                                    int itemWidth,
                                    int itemHeight,
                                    int backgroundDrawable) {
        View view = View.inflate(context, layoutId, null);
        view.measure(MeasureSpec.makeMeasureSpec(itemWidth, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(itemHeight, MeasureSpec.EXACTLY));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        ImageView image = (ImageView) view.findViewById(imageId);
        TextView text = (TextView) view.findViewById(textId);
        image.setImageDrawable(imageDrawable);
        text.setText(title);
        view.setBackgroundResource(backgroundDrawable);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.save();
        view.draw(canvas);
        canvas.restore();
        return bitmap;

    }


}
