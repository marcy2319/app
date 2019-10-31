package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import pl.droidsonroids.gif.GifImageView;

public class LightActivity extends AppCompatActivity implements SensorEventListener {
    private GifImageView display_img;
    private ConstraintLayout container;
    private Sensor sensor = null;
    private SensorManager sensorManager = null;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light);

        container = findViewById(R.id.img_container);

        display_img = new GifImageView(this);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        Log.e("aaa", "aaa : value " + event.values[0]);
        if (sensor != null)
            if(event.values[0] < 300 && !isRunning) {
                isRunning = true;
                container.setVisibility(View.VISIBLE);
            } else if (event.values[0] >= 300 && isRunning) {
                isRunning = false;
                container.setVisibility(View.INVISIBLE);
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