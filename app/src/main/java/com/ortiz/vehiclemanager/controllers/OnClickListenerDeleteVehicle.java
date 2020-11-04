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
import com.ortiz.vehiclemanager.models.FirebaseDatabaseManager;

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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.vehicle_delete_by_id_form, null, false);

        final EditText editTextVehicleId = (EditText) formElementsView.findViewById(R.id.editTextVehicleDeleteId);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementsView);
        builder.setTitle("Delete Vehicle By ID");
        builder.setPositiveButton("Delete",
                (dialog, which) -> {
                    // I learned that leaving this empty is needed for some of the older version of Android Studio
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String sVehicleId = editTextVehicleId.getText().toString().trim();

            // This will handle the input validation, to make sure the user will not give any null or empty values
            if(sVehicleId.length() != 0 && !sVehicleId.isEmpty() && sVehicleId != null ){

                FirebaseDatabaseManager firebaseDatabaseManager = new FirebaseDatabaseManager();
                firebaseDatabaseManager.deleteVehicleById(sVehicleId);

                dialog.cancel();
                Toast.makeText(context, "Vehicle Deleted Successfully",Toast.LENGTH_LONG).show();
            }
            else {
                editTextVehicleId.setError("Text Can't be empty");
            }

        });


    }
}
