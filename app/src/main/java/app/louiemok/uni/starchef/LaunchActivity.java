package app.louiemok.uni.starchef;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.louiemok.uni.starchef.view.HomeActivity;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        int time = 2000;
        Handler handler = new Handler();
        handler.postDelayed ( new Runnable() {
            @Override
            public void run () {
                Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        },time );

    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

}
