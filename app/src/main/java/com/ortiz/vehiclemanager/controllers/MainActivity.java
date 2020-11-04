package com.ortiz.vehiclemanager.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ortiz.vehiclemanager.controllers.OnClickListenerAddVehicle;
import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.controllers.OnClickListenerDeleteVehicle;
import com.ortiz.vehiclemanager.controllers.OnClickListenerEditVehicle;


/**
 * This MainActivity is where the View sits. This class's main purpose
 * is to contain our views and listen if they are clicked or not. If they
 * are clicked then they will get handled by another class.
 *
 * 10/31/2020
 * Robert Ortiz
 */
public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebaseDatabase;
    private RecyclerView oVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        oVehicleList = findViewById(R.id.vehicle_list);

        // Define the buttons and instantiate another class to control the button click
        Button buttonAddVehicle = (Button) findViewById(R.id.buttonAddVehicle);
        buttonAddVehicle.setOnClickListener(new OnClickListenerAddVehicle());

        Button buttonEditVehicle = (Button) findViewById(R.id.buttonEditVehicle);
        buttonEditVehicle.setOnClickListener(new OnClickListenerEditVehicle());

        Button buttonDeleteVehicle = (Button) findViewById(R.id.buttonDeleteVehicle);
        buttonDeleteVehicle.setOnClickListener(new OnClickListenerDeleteVehicle());

    }
}