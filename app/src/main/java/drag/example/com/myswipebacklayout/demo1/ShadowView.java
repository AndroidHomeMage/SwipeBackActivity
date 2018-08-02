package drag.example.com.myswipebacklayout.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

import drag.example.com.myswipebacklayout.R;

/**
 * Created by mayong on 2018/8/1.
 *    效果图   screen/3E94E3C5930AD90EA372B5EF6EADBA44.gif
 */

public class ShadowView extends RelativeLayout {

    private Drawable drawable;
    private ColorDrawable lineDrawable;
    Rect rect = new Rect();
    private int borderwidth = 3;
    private int spacex = 0;
    private int spacey = 0;
    private int spaceW;
    private int spaceH;
    private Random random;

    public ShadowView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ShadowView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ShadowView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        setWillNotDraw(false);
        drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        lineDrawable = new ColorDrawable(Color.RED);
        random = new Random(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean det = super.drawChild(canvas, child, drawingTime);
        drawShadle(canvas, child);
        return det;
    }

    private void drawShadle(Canvas canvas, View child) {
        drawBorder(canvas, child);
        drawAllChild(canvas, child);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                performClick();//如果你想给控件设置点击事件要加上这个，不然控件不能响应点击事件
                break;
            case MotionEvent.ACTION_UP:
                lineDrawable.setColor(Color.rgb(random.nextInt() + 50, random.nextInt() + 50, random.nextInt() + 50));
                performDraw(event.getX(), event.getY());
                break;
        }
        return true;

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void performDraw(float x, float y) {
        getPositionByXY(x, y);
    }

    private void getPositionByXY(float x, float y) {
        spacex = (int) (x / spaceW);
        spacey = (int) (y / spaceH);
        invalidate();
    }

    private void drawBorder(Canvas canvas, View child) {
        Rect r = rect;
        child.getHitRect(r);//获取子控件的绘制区域
        lineDrawable.setBounds(0, 0, borderwidth, r.bottom);//设置lineDrawable的绘制区域
        lineDrawable.draw(canvas);//将lineDrawable绘制到画布上
        lineDrawable.setBounds(0, 0, r.right, borderwidth);
        lineDrawable.draw(canvas);
        lineDrawable.setBounds(r.right - borderwidth, 0, r.right, r.bottom);
        lineDrawable.draw(canvas);
        lineDrawable.setBounds(0, r.bottom - borderwidth, r.right, r.bottom);
        lineDrawable.draw(canvas);
        lineDrawable.setBounds(0, r.bottom / 2 - 1, r.right, r.bottom / 2 + 1);
        lineDrawable.draw(canvas);
        lineDrawable.setBounds(r.right / 2 - 1, 0, r.right / 2 + 1, r.bottom);
        lineDrawable.draw(canvas);
    }

    private void drawAllChild(Canvas canvas, View child) {
        Rect r = rect;
        child.getHitRect(r);
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                if (!(x == spacex && y == spacey)) {
                    Rect childRect = getRectByPosition(child, x, y);
                    drawable.setBounds(childRect.left + 7, childRect.top + 7, childRect.right - 7, childRect.bottom - 7);
                    drawable.draw(canvas);
                }
            }
        }
    }

    private Rect getRectByPosition(View child, int x, int y) {
        Rect rChild = rect;
        child.getHitRect(rChild);
        spaceW = rChild.right / 2;
        spaceH = rChild.height() / 2;
        Rect r = new Rect();
        r.left = spaceW * x;
        r.top = spaceH * y;
        r.right = spaceW * (x + 1);
        r.bottom = spaceH * (y + 1);
        return r;
    }
}
