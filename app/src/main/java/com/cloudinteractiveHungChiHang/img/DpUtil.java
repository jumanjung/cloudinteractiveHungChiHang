package com.cloudinteractiveHungChiHang.img;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by MQ on 2016/12/16.
 */

public class DpUtil {
    /**
     * dp to px
     *
     * @param context Context
     * @param dp      dp
     * @return px
     */
    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    /**
     * sp to px
     *
     * @param context Context
     * @param sp      sp
     * @return px
     */
    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context Context
     * @return 屏幕宽度（像素）
     */
    public static int getScreenSizeWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context Context
     * @return 屏幕高度（像素）
     */
    public static int getScreenSizeHeight(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.heightPixels;
    }
}
