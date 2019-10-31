package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;

public class LightActivity extends AppCompatActivity implements SensorEventListener {
    private GifImageView display_img;
    private Sensor sensor = null;
    private SensorManager sensorManager = null;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);

        display_img = new GifImageView(this);
        display_img.setImageResource(R.mipmap.mg);
        display_img.setVisibility(View.INVISIBLE);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.values[0] < 30 && !isRunning){
            isRunning = true;
            display_img.setVisibility(View.VISIBLE);
        }
        else {
            isRunning = false;
            display_img.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }


    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}