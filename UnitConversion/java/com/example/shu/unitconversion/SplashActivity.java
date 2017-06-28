package com.example.shu.unitconversion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by shu on 2017/6/20.
 * APP启动页
 */

public class SplashActivity extends Activity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.splash_activity);
        
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                Intent  intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                        }
                }, 3000);
        }
}
