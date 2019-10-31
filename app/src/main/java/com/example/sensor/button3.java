package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class button3 extends AppCompatActivity implements SensorEventListener{

    private TextView valueBar, txt;
    private ImageView ImageView2, ImageView3, ImageView4, ImageView5;
    private SensorManager sensorManager;
    private Sensor pressureSensor;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values;
        txt.setText(String.format("%.3f mbar", values[0]));
        valueBar.setText("The barometer says...");
        if (values[0] < 1000) {
            ImageView2.setVisibility(View.VISIBLE);
        }
        else if(1001 < values[0] && values[0] < 1500){
            ImageView3.setVisibility(View.VISIBLE);
        }
        else {
            ImageView4.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }






}
