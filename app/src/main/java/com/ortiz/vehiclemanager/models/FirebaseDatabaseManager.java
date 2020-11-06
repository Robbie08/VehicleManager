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
import com.ortiz.vehiclemanager.interfaces.FirebaseManager;


import java.util.ArrayList;

/**
 * This class was created to handle any transactions between the Firebase Database and
 * the application. This will allow our Database logic to not be implemented in the
 * controller classes.
 *
 * Robert Ortiz
 * 11/4/2020
 */

public class FirebaseDatabaseManager implements FirebaseManager {
    Vehicle vehicle;
    DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
    String sVehicleId;

    public FirebaseDatabaseManager(Vehicle vehicle) {
        this.vehicle = vehicle;
        sVehicleId = Integer.toString(vehicle.getId());
    }

    public FirebaseDatabaseManager() {
    }

    /**
     * This function will edit the given vehicle object in the firebase database
     */
    public void editVehicle() {
        oVehicleReference.child(sVehicleId).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(sVehicleId).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(sVehicleId).child("Year").setValue(vehicle.getYear());
    }

    /**
     * This function will delete the vehicle form firebase database
     * @param sVehicleId: the vehicle id of the vehicle we wish to delete
     */
    public void deleteVehicleById(String sVehicleId) {
        oVehicleReference.child(sVehicleId).removeValue();
    }

    /**
     * This function will add a vehicle the the firebase database
     */
    public void addVehicle() {
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Year").setValue(vehicle.getYear());
        oVehicleReference.child(Integer.toString(vehicle.getId())).child("Id").setValue(Integer.toString(vehicle.getId()));
    }


    /**
     * This function will return the Vehicles from the database and update the array list of vehicle
     * objects in JSON form parsed as strings.
     *
     * @param oVehicleList: An empty list that will contain our vehicles string format
     * @param adapter: the adapter that helps our list update.
     */
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

    /**
     * This function will fetch the vehicle by passed in Id value.
     *
     * @param sVehicleId: a string type ID that this function will use to query the database
     * @param view: the view that will get passed into the new instance
     */
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
