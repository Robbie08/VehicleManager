package com.ortiz.vehiclemanager.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseManager {
    Vehicle vehicle;
    DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
    String sVehicleId;
    public FirebaseDatabaseManager(Vehicle vehicle) {
        this.vehicle = vehicle;
        sVehicleId = Integer.toString(vehicle.getId());
    }



    public void editVehicle() {
        oVehicleReference.child(sVehicleId).child("Make").setValue(vehicle.getMake());
        oVehicleReference.child(sVehicleId).child("Model").setValue(vehicle.getModel());
        oVehicleReference.child(sVehicleId).child("Year").setValue(vehicle.getYear());
    }
}
