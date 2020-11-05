package com.sunfb.onekeygray.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunfb.onekeygray.impl.GrayFrameLayout;
import com.sunfb.onekeygray.impl.GrayController;

/**
 * 基础类
 * 技术来源： https://my.oschina.net/u/4390958/blog/3220083
 * http://www.cocoachina.com/cms/wap.php?action=article&id=93543
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            invalidateGray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            invalidateGray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        try {
            boolean isBlackWhiteUI = GrayController.getInStance().isState();
            if (isBlackWhiteUI) {
                if ("FrameLayout".equals(name)) {
                    int count = attrs.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        String attributeName = attrs.getAttributeName(i);
                        String attributeValue = attrs.getAttributeValue(i);
                        if (attributeName.equals("id")) {
                            int id = Integer.parseInt(attributeValue.substring(1));
                            String idVal = getResources().getResourceName(id);
                            if ("android:id/content".equals(idVal)) {
                                GrayFrameLayout grayFrameLayout = new GrayFrameLayout(context, attrs);
                               // 如果 Activity的 Window 设置了 background ,因为我们处理的是 content view，肯定在 window 之下，肯定覆盖不到 window 的 backgroud。
                                grayFrameLayout.setBackgroundDrawable(getWindow().getDecorView().getBackground());
                                //如果你是theme 中设置的 windowBackground，那么需要从 theme 里面提取 drawable，参考代码如下：
                                TypedValue a = new TypedValue();
                                getTheme().resolveAttribute(android.R.attr.windowBackground, a, true);
                                if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                                    // windowBackground is a color
                                    int color = a.data;
                                } else {
                                    // windowBackground is not a color, probably a drawable
                                    Drawable c = getResources().getDrawable(a.resourceId);
                                }
                                return grayFrameLayout;
                            }
                        }
                    }
                }
            }
            return super.onCreateView(name, context, attrs);
        } catch (Exception e) {
            e.printStackTrace();
            return super.onCreateView(name, context, attrs);
        }
    }

    public void invalidateGray(){
        boolean invalidate = GrayController.getInStance().isState();
        if (invalidate) {
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
        }else {
            GrayController.getInStance().switchNormalForUI(this);
        }
    }


}
