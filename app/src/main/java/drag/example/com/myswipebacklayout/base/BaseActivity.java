package drag.example.com.myswipebacklayout.base;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import drag.example.com.myswipebacklayout.R;

/**
 * Created by mayong on 2018/8/2.
 *
 */

public class BaseActivity extends AppCompatActivity {
    private SwipeLayout swipeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundDrawable(null);
        attachSwipeLayoutToActivity();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeLayout.addachToActivity(this);//这行代码必须在这里调用，否则swipelayout把DecorView里面的内容添加到SwipeLayout里面
    }

    public void attachSwipeLayoutToActivity() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().getDecorView().setBackgroundDrawable(null);
        swipeLayout = (SwipeLayout) LayoutInflater.from(this).inflate(
               R.layout.layout_swipe, null);
    }

    public SwipeLayout getSwipeView() {
        return swipeLayout;
    }
}
