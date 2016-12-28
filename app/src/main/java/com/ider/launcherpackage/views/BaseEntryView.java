package com.ider.launcherpackage.views;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ider.launcherpackage.common.BitmapTools;


public abstract class BaseEntryView extends ImageView implements View.OnClickListener, View.OnLongClickListener{

    public BitmapTools mBitmapTools;
    public Context mContext;

    public BaseEntryView(Context context) {
        this(context, null);
    }

    public BaseEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOnClickListener(this);
        setOnLongClickListener(this);
        mBitmapTools = BitmapTools.getInstance();
    }

    public abstract void setDefault();

    public abstract void setBitmap();

    public abstract void updateSelf();

    public abstract void onClick();

    public abstract void onLongClick();


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        updateSelf();
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        ObjectAnimator animator;
        if(gainFocus) {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.1f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.1f);
            animator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY);
            animator.setDuration(250);
            bringToFront();
        } else {
            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.1f, 1f);
            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1.1f, 1f);
            animator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY);
            animator.setDuration(100);
        }
        animator.start();
    }

    @Override
    public void onClick(View view) {
        onClick();
    }

    @Override
    public boolean onLongClick(View view) {
        onLongClick();
        return true;
    }
}
