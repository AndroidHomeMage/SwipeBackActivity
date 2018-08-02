package drag.example.com.myswipebacklayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_first).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });
    }
}
