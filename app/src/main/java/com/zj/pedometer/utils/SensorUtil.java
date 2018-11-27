package com.zj.pedometer.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

/**
 * create by zj on 2018/11/26
 */
public class SensorUtil {

    //判断设备是否支持计步
    public static boolean isReportSensor(Context context){
        SensorManager sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor countSensor =  sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor detectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        return countSensor!=null||detectorSensor!=null;

    }
}
