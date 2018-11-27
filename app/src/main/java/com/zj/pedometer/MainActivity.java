package com.zj.pedometer;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zj.pedometer.databinding.ActivityMainBinding;
import com.zj.pedometer.utils.SensorUtil;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int size;
    public static void launchActivity(Context context){
        context.startActivity(new Intent(context,MainActivity.class));
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==1){
                size++;
                if (size>100){
                    return false;
                }
                binding.circleBar.setData(size);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil. setContentView(this,R.layout.activity_main);
        binding.setPresenter(this);


        if (SensorUtil.isReportSensor(this)){
            Toast.makeText(this,"开始你的表演",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"此设备不支持计步",Toast.LENGTH_SHORT).show();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (size<100){
                    try {
                        Thread.sleep(1000);
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }



}
