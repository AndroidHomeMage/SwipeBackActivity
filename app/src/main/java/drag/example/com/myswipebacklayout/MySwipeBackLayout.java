package drag.example.com.myswipebacklayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by mayong on 2018/8/1.
 */

public class MySwipeBackLayout extends FrameLayout {

    private ViewDragHelper helper;

    public MySwipeBackLayout(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public MySwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MySwipeBackLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        helper = ViewDragHelper.create(this,new MyDragCallback());
    }


    class MyDragCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return true;
        }


        @Override
        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }
    }
}
