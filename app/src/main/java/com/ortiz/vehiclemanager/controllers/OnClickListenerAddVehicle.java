package com.ortiz.vehiclemanager.controllers;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.models.Vehicle;


/**
 * OnClickListenerAddVehicle instance
 * Roberto Ortiz
 * 10/31/20
 * This class will handle button clicks for the Add Vehicle button on
 * the view.
 *
 * First display a form for the user to fill out. This form will prompt
 * the user for the Make, Model, Year, and Id of the Vehicle.
 */
public class OnClickListenerAddVehicle implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.vehicle_entry_form,null,false);

        // instantiate the four EditText items from our form
        final EditText editTextVehicleMake = (EditText) formElementsView.findViewById(R.id.editTextVehicleMake);
        final EditText editTextVehicleModel = (EditText) formElementsView.findViewById(R.id.editTextVehicleModel);
        final EditText editTextVehicleYear = (EditText) formElementsView.findViewById(R.id.editTextVehicleYear);
        final EditText editTextVehicleId = (EditText) formElementsView.findViewById(R.id.editTextVehicleId);


        // Display a alert dialog box to the user, this will display the entry form to add the vehicle
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setView(formElementsView);
        builder.setTitle("Add Vehicle");
        builder.setPositiveButton("Add",
                (dialog, which) -> {
                    // learned that some older versions of android studio need this empty onClick event to not crash
                });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String sVehicleMake, sVehicleModel, sVehicleYear, sVehicleId;
            int iVehicleYear, iVehicleId;

            // Once the user has typed in their information we must collect it
            sVehicleMake = editTextVehicleMake.getText().toString().trim();
            sVehicleModel = editTextVehicleModel.getText().toString().trim();
            sVehicleYear = editTextVehicleYear.getText().toString().trim();
            sVehicleId = editTextVehicleId.getText().toString().trim();


            DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");

            // handle empty input validation before we send any data to the Database
            if(sVehicleId.length() == 0 || sVehicleId == null){
                editTextVehicleId.setError("Text can't be empty");
            }
            if(sVehicleYear.length() == 0 || sVehicleYear == null){
                editTextVehicleYear.setError("Text can't be empty");
            }
            if(sVehicleModel.length() == 0 || sVehicleModel == null){
                editTextVehicleModel.setError("Text can't be empty");
            }
            if(sVehicleMake.length() == 0 || sVehicleMake == null){
                editTextVehicleMake.setError("Text can't be empty");
            }
            else if(sVehicleId.length() != 0 && sVehicleMake.length() != 0
                    && sVehicleModel.length() != 0 && sVehicleYear.length() != 0){
                // we need to covert year and id to int so that we can create our Vehicle object
                iVehicleYear = Integer.parseInt(sVehicleYear);
                iVehicleId = Integer.parseInt(sVehicleId);
                Vehicle vehicle = new Vehicle(iVehicleId,iVehicleYear,sVehicleMake,sVehicleModel);

                oVehicleReference.child(Integer.toString(vehicle.getId())).child("Make").setValue(vehicle.getMake());
                oVehicleReference.child(Integer.toString(vehicle.getId())).child("Model").setValue(vehicle.getModel());
                oVehicleReference.child(Integer.toString(vehicle.getId())).child("Year").setValue(vehicle.getYear());
                oVehicleReference.child(Integer.toString(vehicle.getId())).child("Id").setValue(vehicle.getId());

                dialog.cancel();
                Toast.makeText(context, "Vehicle Added Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
}