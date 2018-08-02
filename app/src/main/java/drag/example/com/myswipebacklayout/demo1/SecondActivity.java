package drag.example.com.myswipebacklayout.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import drag.example.com.myswipebacklayout.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.shadow).setOnClickListener(v->{
            Log.e("tag","被点击" );
        });
    }
}
