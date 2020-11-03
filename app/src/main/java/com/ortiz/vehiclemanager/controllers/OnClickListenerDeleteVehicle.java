package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ortiz.vehiclemanager.R;

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
                                dialog.cancel();
                            }
                        }).show();


    }
}
