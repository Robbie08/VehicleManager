package com.ortiz.vehiclemanager.views;

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


public class MainActivity extends AppCompatActivity {

    private DatabaseReference firebaseDatabase;
    private RecyclerView oVehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        oVehicleList = findViewById(R.id.vehicle_list);

        // create button on click
        Button buttonAddVehicle = (Button) findViewById(R.id.buttonAddVehicle);
        buttonAddVehicle.setOnClickListener(new OnClickListenerAddVehicle());

        Button buttonEditVehicle = (Button) findViewById(R.id.buttonEditVehicle);
        buttonEditVehicle.setOnClickListener(new OnClickListenerEditVehicle());

        Button buttonDeleteVehicle = (Button) findViewById(R.id.buttonDeleteVehicle);
        buttonDeleteVehicle.setOnClickListener(new OnClickListenerDeleteVehicle());

    }
}