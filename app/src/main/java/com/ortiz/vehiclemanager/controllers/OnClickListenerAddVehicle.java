package com.ortiz.vehiclemanager.controllers;
import android.app.AlertDialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.interfaces.FirebaseManager;
import com.ortiz.vehiclemanager.interfaces.Utils;
import com.ortiz.vehiclemanager.models.FirebaseDatabaseManager;
import com.ortiz.vehiclemanager.models.MyUtils;
import com.ortiz.vehiclemanager.models.Vehicle;

import java.util.UUID;


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
            String sVehicleMake, sVehicleModel, sVehicleYear;
            int iVehicleYear, iVehicleId;

            // Once the user has typed in their information we must collect it
            sVehicleMake = editTextVehicleMake.getText().toString().trim();
            sVehicleModel = editTextVehicleModel.getText().toString().trim();
            sVehicleYear = editTextVehicleYear.getText().toString().trim();

            if(sVehicleYear.length() == 0){
                editTextVehicleYear.setError("Text can't be empty");
            }
            if(sVehicleModel.length() == 0){
                editTextVehicleModel.setError("Text can't be empty");
            }
            if(sVehicleMake.length() == 0){
                editTextVehicleMake.setError("Text can't be empty");
            }
            else if( sVehicleMake.length() != 0 && sVehicleModel.length() != 0 && sVehicleYear.length() != 0){

                iVehicleYear = Integer.parseInt(sVehicleYear);
                Utils myUtils = new MyUtils();
                iVehicleId = myUtils.generateUniqueId();
                if(iVehicleYear > 2050){
                    editTextVehicleYear.setError("Vehicle Year can't be greater than 2050");
                }
                if(iVehicleYear < 1950){
                    editTextVehicleYear.setError("Vehicle Year can't be less than 1950");
                }
                else if(iVehicleYear >= 1950 && iVehicleYear <= 2050){
                    Vehicle vehicle = new Vehicle(iVehicleId,iVehicleYear,sVehicleMake,sVehicleModel);

                    FirebaseManager firebaseManager = new FirebaseDatabaseManager(vehicle);
                    firebaseManager.addVehicle();

                    dialog.cancel();
                    Toast.makeText(context, "Vehicle Added Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}