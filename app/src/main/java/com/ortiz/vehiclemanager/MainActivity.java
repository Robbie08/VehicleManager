package com.ortiz.vehiclemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonAddVehicle = (Button) findViewById(R.id.buttonAddVehicle);
        buttonAddVehicle.setOnClickListener(new OnClickListenerAddVehicle());
    }
}