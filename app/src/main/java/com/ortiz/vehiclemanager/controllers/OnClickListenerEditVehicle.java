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
 * This instance will be called when the EditVehicle button is clicked.
 * The main purpose of this class is to get the user input from the edit text
 * and then pass it to the next alert dialog where the user will then be able to
 * update their vehicle information
 *
 * 11/3/2020
 * Robert Ortiz
 */
public class OnClickListenerEditVehicle implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementView = inflater.inflate(R.layout.vehicle_edit_by_id_form, null, false);

        EditText editTextVehicleId = (EditText) formElementView.getRootView().findViewById(R.id.editTextVehicleEditById);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementView);
        builder.setTitle("Edit Vehicle by Id");
        builder.setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

        // this will allow us to override the instance above and treat the alert dialog as the current view
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sVehicleId = editTextVehicleId.getText().toString().trim();
                // This will handle the input validation, to make sure the user will not give any null or empty values
                if (sVehicleId.length() != 0 && !sVehicleId.isEmpty() && sVehicleId != null) {
                    // Call another instance of an alert dialog where the user can update the vehicle information
                    new OnClickListenerEditVehicleForm(formElementView, sVehicleId);
                    dialog.cancel();
                } else {
                    editTextVehicleId.setError("Text Can't be empty");
                }
            }
        });


    }
}

