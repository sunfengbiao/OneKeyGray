package com.sunfb.onekeygray.impl;


import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.View;

import com.sunfb.onekeygray.base.BaseActivity;

public final class GrayController {

    private static volatile GrayController inStance;

    private boolean state = false;

    /**
     * 构造方法
     */
    private GrayController() {

    }

    public static GrayController getInStance() {
        if (inStance == null) {
            synchronized (GrayController.class) {
                inStance = new GrayController();
            }
        }
        return inStance;
    }


    /**
     * 今天是否是哀悼日期，哀悼日期是后台配置的
     *
     * @return true 是 ；false 否
     */
    public boolean isState() {
        return state;
    }


    /**
     * 设置一键指挥开关 ，亦可根据实际需要做异步处理
     *
     * @param state
     */
    public void setState(boolean state) {
        //TODO  配置处理逻辑
        this.state = state;
    }
    /**
     * 恢复正常UI状态 与switchBlackWhiteForUI方法配对使用
     *
     * @param baseActivity @param baseActivity {@link BaseActivity}
     */
    public void switchNormalForUI(BaseActivity baseActivity) {
        if (baseActivity == null) return;
        View decorView = baseActivity.getWindow().getDecorView();
        decorView.invalidate();
        decorView.setLayerType(View.LAYER_TYPE_NONE, null);

    }

//    /**
//     * 一键置灰的实现
//     *
//      * @param baseActivity @param baseActivity {@link BaseActivity}
//     */
//    public void switchLamentForUI(BaseActivity baseActivity) {
//        if (baseActivity == null) return;
//        if (isState()) {
//            View decorView = baseActivity.getWindow().getDecorView();
//            decorView.invalidate();
//            Paint paint = new Paint();
//            ColorMatrix cm = new ColorMatrix();
//            cm.setSaturation(0);
//            paint.setColorFilter(new ColorMatrixColorFilter(cm));
//            decorView.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
//
//
//        }
//    }


}
