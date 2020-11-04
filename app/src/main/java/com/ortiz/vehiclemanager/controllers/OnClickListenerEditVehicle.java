package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

        // we have to create alert dialog
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementView = inflater.inflate(R.layout.vehicle_edit_by_id_form,null,false);

        EditText editTextVehicleId = (EditText) formElementView.getRootView().findViewById(R.id.editTextVehicleEditById);

        new AlertDialog.Builder(context)
                .setView(formElementView)
                .setTitle("Edit Vehicle by Id")
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Call another alert dialog where the user can update the vehicle information
                                String sVehicleId = editTextVehicleId.getText().toString().trim();
                                new OnClickListenerEditVehicleForm(formElementView, sVehicleId);
                                dialog.cancel();
                            }
                        }).show();



    }
}
