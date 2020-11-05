package com.ortiz.vehiclemanager.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.models.FirebaseDatabaseManager;


import java.util.ArrayList;


/**
 * This MainActivity is where the View sits. This class's main purpose
 * is to contain our views and listen if they are clicked or not. If they
 * are clicked then they will get handled by another class.
 *
 * 10/31/2020
 * Robert Ortiz
 */


public class MainActivity extends AppCompatActivity {
    private ListView oVehicleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oVehicleListView = findViewById(R.id.vehicle_list);

        // Define the buttons and instantiate another class to control the button click
        Button buttonAddVehicle = (Button) findViewById(R.id.buttonAddVehicle);
        buttonAddVehicle.setOnClickListener(new OnClickListenerAddVehicle());

        Button buttonEditVehicle = (Button) findViewById(R.id.buttonEditVehicle);
        buttonEditVehicle.setOnClickListener(new OnClickListenerEditVehicle());

        Button buttonDeleteVehicle = (Button) findViewById(R.id.buttonDeleteVehicle);
        buttonDeleteVehicle.setOnClickListener(new OnClickListenerDeleteVehicle());

        Button buttonGetVehicle = (Button) findViewById(R.id.buttonGetVehicle);
        buttonGetVehicle.setOnClickListener(new OnClickListenerGetVehicle());

        // The following code will call our Firebase manager and retrieve the data from Firebase
        ArrayList<String> oVehicleList = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, oVehicleList);
        FirebaseDatabaseManager firebaseDatabaseManager = new FirebaseDatabaseManager();
        firebaseDatabaseManager.getDatabaseItems(oVehicleList, adapter);
        oVehicleListView.setAdapter(adapter); // add the values to our list

    }
}