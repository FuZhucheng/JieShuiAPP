package com.baidumap.test.tap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ant.liao.GifView;

public class SplashActivity extends Activity {
    private GifView gifView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gifView= (GifView) findViewById(R.id.gifview);
        gifView.setGifImage(R.drawable.splash_gif);
        gifView.setShowDimension(1100,2000);
        gifView.setGifImageType(GifView.GifImageType.COVER);
        gifView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },2000);
    }
}
