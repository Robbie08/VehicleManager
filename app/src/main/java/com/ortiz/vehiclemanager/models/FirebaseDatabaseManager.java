package com.ortiz.vehiclemanager.models;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortiz.vehiclemanager.controllers.OnClickListenerAddVehicle;
import com.ortiz.vehiclemanager.controllers.OnClickListenerGetVehicle;
import com.ortiz.vehiclemanager.controllers.OnClickListenerGetVehicleDisplay;


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
    int maxId = 0;
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

        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Year").setValue(vehicle.getYear());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Id").setValue(Integer.toString(vehicle.getId()));
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

    public void getVehicleById(String sVehicleId, View view) {
        DatabaseReference oVehicleIdRef = oVehicleReference.child(sVehicleId);
        oVehicleIdRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("Make").getValue() != null && snapshot.child("Model").getValue() != null
                        && snapshot.child("Year").getValue() != null && snapshot.child("Id").getValue() != null){
                    String vehicleMake = snapshot.child("Make").getValue().toString();
                    String vehicleModel = snapshot.child("Model").getValue().toString();
                    String vehicleYear = snapshot.child("Year").getValue().toString();
                    String vehicleId = snapshot.child("Id").getValue().toString();


                    if(vehicleId.length() != 0 && vehicleYear.length() != 0 && vehicleMake.length() != 0 && vehicleModel.length() != 0) {
                        int iVehicleId = Integer.parseInt(vehicleId);
                        int iVehicleYear = Integer.parseInt(vehicleYear);

                        // send the information the the OnClickListenerDisplay class
                        vehicle = new Vehicle(iVehicleId,iVehicleYear,vehicleMake,vehicleModel);
                        new OnClickListenerGetVehicleDisplay(view,vehicleId,vehicle);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
