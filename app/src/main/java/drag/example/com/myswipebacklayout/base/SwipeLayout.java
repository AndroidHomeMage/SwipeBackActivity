package drag.example.com.myswipebacklayout.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import drag.example.com.myswipebacklayout.R;

/**
 * Created by mayong on 2018/8/2.
 * 实现滑动的控件
 */

public class SwipeLayout extends RelativeLayout {

    private ViewDragHelper helper;
    private int contentLeft;
    private View child;
    private int OVERSCROLL = 20;
    private OnContentOuterHorizonalListener onContentOuterHorizonalListener;

    public SwipeLayout(Context context) {
        super(context);
        init();
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        helper = ViewDragHelper.create(this, new SwipeDragCallback());
        helper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    public void addachToActivity(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View childView = decorView.getChildAt(0);
        decorView.removeView(childView);
        addView(childView);
        setChildView(childView);
        decorView.addView(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        child.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        child.layout(contentLeft, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        helper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return helper.shouldInterceptTouchEvent(ev);
    }

    public void setChildView(View childView) {
        this.child = childView;
    }

    private static final int mScrimColor = 0x99000000;

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean superdraw = super.drawChild(canvas, child, drawingTime);
        if(isScrolling){
            drawScrim(canvas);
        }
        return superdraw;
    }

    private void drawScrim(Canvas canvas) {
        canvas.clipRect(0, 0, contentLeft, child.getMeasuredHeight());
        canvas.drawColor(getResources().getColor(R.color.bg));
    }


    boolean isScrolling;

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (helper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {

        }
    }

    public void setOnContentOuterHorizonalListener(OnContentOuterHorizonalListener onContentOuterHorizonalListener) {
        this.onContentOuterHorizonalListener = onContentOuterHorizonalListener;
    }

    class SwipeDragCallback extends ViewDragHelper.Callback {

        int left = 0;
        boolean isEdgeTouched;

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            isEdgeTouched = helper.isEdgeTouched(ViewDragHelper.EDGE_LEFT, pointerId);
            if(isEdgeTouched){
                isScrolling=true;
            }
            return isEdgeTouched;
        }

        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            contentLeft = left;
            if (left > changedView.getWidth()) {
                if (onContentOuterHorizonalListener != null) {
                    onContentOuterHorizonalListener.finish();
                    isScrolling=false;
                }
            } else {
                invalidate();
            }
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int width = releasedChild.getMeasuredWidth();

            if (xvel > 0) {
                left = width + OVERSCROLL;
            } else {
                left = 0;
            }
            helper.settleCapturedViewAt(left, 0);
            invalidate();
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }
    }
}
