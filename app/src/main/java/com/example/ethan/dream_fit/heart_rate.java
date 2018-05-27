package com.example.ethan.dream_fit;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class heart_rate extends AppCompatActivity implements SensorEventListener, ActivityCompat.OnRequestPermissionsResultCallback{

    private static final int REQUEST_BODY_SENSORS = 200;

    public void showHeartRate(View view){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        if (requestCode == REQUEST_BODY_SENSORS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                showCameraPreview();
            } else {
                Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            }
        } else {
                super.onRequestPermissionsResult(requestCode, permissions,grantResults);
            }
        }



    TextView tv_heart_rate;

    SensorManager sensorManager;

    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        tv_heart_rate = (TextView) findViewById(R.id.tv_heart_rate);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(checkSelfPermission(Manifest.permission.BODY_SENSORS) == PackageManager.PERMISSION_GRANTED){
//            showHeartRatePreview();
        } else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.BODY_SENSORS)){
                Toast.makeText(this, "Body Sensors permissions is needed to show the heart rate.", Toast.LENGTH_SHORT).show();
            }

            //ActivityCompat.requestPermissions(new String[]{Manifest.permission.BODY_SENSORS}, REQUEST_BODY_SENSORS);
            ActivityCompat.requestPermissions(heart_rate.this, new String[]{Manifest.permission.BODY_SENSORS}, REQUEST_BODY_SENSORS);

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if(countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            tv_heart_rate.setText(String.valueOf(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
