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
}
