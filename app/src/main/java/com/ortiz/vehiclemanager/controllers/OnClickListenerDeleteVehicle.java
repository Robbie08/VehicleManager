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

/**
 * OnClickListenerDeleteVehicle instance
 * Roberto Ortiz
 * 11/3/20
 * This class will handle button clicks for the Add Vehicle delete on
 * the view.
 *
 * First display a form for the user to fill out. This form will prompt
 * the user for the Id of the Vehicle.
 *
 * This will then make the request to Firebase Database to delete the item
 * by its id
 */

public class OnClickListenerDeleteVehicle implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getRootView().getContext();

        // we have to inflate the alert dialog
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.vehicle_delete_by_id_form, null, false);

        final EditText editTextVehicleId = (EditText) formElementsView.findViewById(R.id.editTextVehicleDeleteId);

        // create alert dialog to allow for user entry
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Delete Vehicle By ID")
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // create a reference to the DB where our vehicles are stored
                                DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");

                                String sVehicleId = editTextVehicleId.getText().toString().trim();

                                if(sVehicleId.length() != 0 && !sVehicleId.isEmpty()){
                                    oVehicleReference.child(sVehicleId).removeValue();
                                }
                                else{
                                    editTextVehicleId.setError("Text Can't be empty");
                                }
                                dialog.cancel();
                            }
                        }).show();


    }
}
