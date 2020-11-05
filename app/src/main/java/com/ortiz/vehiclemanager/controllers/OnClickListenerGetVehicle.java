package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ortiz.vehiclemanager.R;

public class OnClickListenerGetVehicle implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = view.getRootView().getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formElementView = inflater.inflate(R.layout.vehicle_get_by_id, null, false);
        EditText editTextVehicleId = (EditText) formElementView.getRootView().findViewById(R.id.editTextVehicleGetById);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementView);
        builder.setTitle("Get Vehicle by Id");
        builder.setPositiveButton("Get",
                (dialog, which) -> {
                    // learned that some older versions of android studio need this empty onClick event to not crash
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            String sVehicleId = editTextVehicleId.getText().toString().trim();
            // This will handle the input validation, to make sure the user will not give any null or empty values
            if (sVehicleId.length() != 0 && !sVehicleId.isEmpty() && sVehicleId != null) {
                //implement logic to get the vehicle information and display it in a new alert dialog
                dialog.cancel();
            } else {
                editTextVehicleId.setError("Text Can't be empty");
            }
        });

    }
}
