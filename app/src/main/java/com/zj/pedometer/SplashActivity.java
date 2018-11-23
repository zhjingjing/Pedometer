package com.zj.pedometer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zj.pedometer.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_splash);
        binding.setPresenter(this);

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                MainActivity.launchActivity(SplashActivity.this);
                finish();
            }
        };
        new Timer().schedule(timerTask,2000);
    }
}
