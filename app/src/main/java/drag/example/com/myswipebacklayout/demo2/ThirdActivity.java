package drag.example.com.myswipebacklayout.demo2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

import drag.example.com.myswipebacklayout.R;
import drag.example.com.myswipebacklayout.base.BaseActivity;
import drag.example.com.myswipebacklayout.base.OnContentOuterHorizonalListener;
import drag.example.com.myswipebacklayout.base.SwipeLayout;

public class ThirdActivity extends BaseActivity {
    private static final int VIBRATE_DURATION = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setBackGround();
        findViewById(R.id.tv_third).setOnClickListener(v->{
            startActivity(new Intent(ThirdActivity.this,ThirdActivity.class));
        });
        getSwipeView().setOnContentOuterHorizonalListener(new OnContentOuterHorizonalListener() {
            @Override
            public void finish() {
                ThirdActivity.this.finish();
            }
        });
    }

    private void setBackGround() {
        Random random1 = new Random();
        int index = random1.nextInt(3);
        View container = findViewById(R.id.container);
        switch (index){
            case 0:
                container.setBackgroundResource(R.mipmap.namei3);
                break;
            case 1:
                container.setBackgroundResource(R.mipmap.namei4);
                break;
            case 2:
                container.setBackgroundResource(R.mipmap.namei5);
                break;
        }
    }


}
