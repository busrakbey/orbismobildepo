package com.konumsal.orbisozetmobil.OrtakUI;

/**
 * Created by isahin on 19.10.2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.konumsal.orbisozetmobil.R;


public class PopupWrapper implements OnTouchListener {

    /** Context where to show the popup */
    protected Context context = null;

    /** The popup to show */
    protected PopupWindow window = null;

    /** What to show in the popup */
    protected View root = null;

    /** Parent/anchor View */
    protected View anchor = null;

    /** Optional background */
    protected Drawable background = null;

    /** Window manager */
    private WindowManager windowManager;

    public PopupWrapper(Activity activity) {
        anchor = null;
        context = activity;
        init();
    }

    public PopupWrapper(View anchor) {

        this.anchor = anchor;
        context = anchor.getContext();
        init();
    }

    private void init() {

        window = new PopupWindow(context);
        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setTouchable(true);
        //window.setFocusable(true);
        window.setOutsideTouchable(true);

        // when a touch even happens outside of the window
        // make the window go away
        window.setTouchInterceptor(this);
        windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        onCreate();
    }

    /**
     * Anything you want to have happen when created. Probably should create a
     * view and setup the event listeners on child views.
     */
    protected void onCreate() {
    }

    /**
     * In case there is stuff to do right before displaying.
     */
    protected void onShow() {
    }

    private void preShow() {
        if (root == null) {
            throw new IllegalStateException(
                    "setContentView was not called with a view to display.");
        }
        onShow();

        if (background == null) {
            window.setBackgroundDrawable(new BitmapDrawable());
        } else {
            window.setBackgroundDrawable(background);
        }

        // if using PopupWindow#setBackgroundDrawable this is the only values of
        // the width and hight that make it work
        // otherwise you need to set the background of the root viewgroup
        // and set the popupwindow background to an empty BitmapDrawable
        window.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.setContentView(root);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            window.dismiss();
            return true;
        }
        return false;
    }

    public void setBackgroundDrawable(Drawable background) {
        this.background = background;
    }


    public void setContentView(View root) {
        this.root = root;
        window.setContentView(root);
    }

    /**
     * Will inflate and set the view from a resource id
     *
     * @param layoutResID
     */
    public void setContentView(int layoutResID) {
        LayoutInflater inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(inflator.inflate(layoutResID, null));
    }


    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        window.setOnDismissListener(listener);
    }

    /**
     * Displays like a popdown menu from the anchor view
     */
    public void showLikePopDownMenu() {
        showLikePopDownMenu(0, 0);
    }

    /**
     * Displays like a popdown menu from the anchor view.
     *
     * @param xOffset
     *            offset in X direction
     * @param yOffset
     *            offset in Y direction
     */
    public void showLikePopDownMenu(int xOffset, int yOffset) {
        preShow();

        window.setAnimationStyle(R.anim.m_myanimation_anim);

        window.showAsDropDown(anchor, xOffset, yOffset);
    }

    /**
     * Displays like a QuickAction from the anchor view.
     */
    public void showLikeQuickAction() {
        showLikeQuickAction(0, 0);
    }

    /**
     * Displays like a QuickAction from the anchor view.
     *
     * @param xOffset
     *            offset in the X direction
     * @param yOffset
     *            offset in the Y direction
     */
    public void showLikeQuickAction(int xOffset, int yOffset) {
        preShow();

        window.setAnimationStyle(R.anim.m_myanimation_anim);

        int[] location = new int[2];
        anchor.getLocationOnScreen(location);

        Rect anchorRect = new Rect(location[0], location[1], location[0]
                + anchor.getWidth(), location[1] + anchor.getHeight());

        root.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        int rootWidth = root.getMeasuredWidth();
        int rootHeight = root.getMeasuredHeight();

        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        //int screenHeight = windowManager.getDefaultDisplay().getHeight();

        int xPos = ((screenWidth - rootWidth) / 2) + xOffset;
        int yPos = anchorRect.top - rootHeight + yOffset;

        // display on bottom
        if (rootHeight > anchorRect.top) {
            yPos = anchorRect.bottom + yOffset;
            window.setAnimationStyle(R.anim.m_myanimation_anim);
        }

        window.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
    }

    public void dismiss() {
        window.dismiss();
    }

    public void showAtLocation(int gravity, int x, int y) {
        window.showAtLocation(anchor, gravity, x, y);
    }
}