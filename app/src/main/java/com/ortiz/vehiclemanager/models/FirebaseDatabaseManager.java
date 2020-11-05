package com.ortiz.vehiclemanager.models;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * This class was created to handle any transactions between the Firebase Database and
 * the application. This will allow our Database logic to not be implemented in the
 * controller classes.
 *
 * Robert Ortiz
 * 11/4/2020
 */
public class FirebaseDatabaseManager {
    Vehicle vehicle;
    DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
    String sVehicleId;
    public FirebaseDatabaseManager(Vehicle vehicle) {
        this.vehicle = vehicle;
        sVehicleId = Integer.toString(vehicle.getId());
    }

    public FirebaseDatabaseManager() {
    }

    public void editVehicle() {
        oVehicleReference.child(sVehicleId).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(sVehicleId).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(sVehicleId).child("Year").setValue(vehicle.getYear());
    }

    public void deleteVehicleById(String sVehicleId) {
        oVehicleReference.child(sVehicleId).removeValue();
    }

    public void addVehicle() {
        oVehicleReference.child(sVehicleId).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(sVehicleId).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(sVehicleId).child("Year").setValue(vehicle.getYear());
        oVehicleReference.child(sVehicleId).child("Id").setValue(vehicle.getId());
    }

    public void getDatabaseItems(ArrayList<String> oVehicleList, ArrayAdapter adapter) {
        oVehicleReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                oVehicleList.clear();
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    oVehicleList.add(snapshot1.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
