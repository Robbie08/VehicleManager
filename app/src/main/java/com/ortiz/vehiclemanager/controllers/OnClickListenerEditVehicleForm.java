package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.interfaces.FirebaseManager;
import com.ortiz.vehiclemanager.models.FirebaseDatabaseManager;
import com.ortiz.vehiclemanager.models.Vehicle;

/**
 * This instance will be called when the positive button from the EditFormById
 * form gets clicked. This form will modify the existing data of our database
 * with the given id.
 *
 * 11/3/2020
 * Robert Ortiz
 */
public class OnClickListenerEditVehicleForm implements View.OnClickListener {

    View viewItem;
    Context context;
    String sVehicleId , sVehicleModel="", sVehicleMake="", sVehicleYear="";
    int iVehicleId = 0, iVehicleYear =0;

    public OnClickListenerEditVehicleForm(View viewById, String sVehicleId) {
        this.viewItem = viewById;
        this.sVehicleId = sVehicleId;
        this.context = viewById.getRootView().getContext();
        onClick(viewById);
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementView = inflater.inflate(R.layout.vehicle_edit_form,null,false);

        EditText editTextVehicleYearEdit = (EditText) formElementView.findViewById(R.id.editTextVehicleEditYear);
        EditText editTextVehicleModelEdit = (EditText) formElementView.findViewById(R.id.editTextVehicleEditModel);
        EditText editTextVehicleMakeEdit = (EditText) formElementView.findViewById(R.id.editTextVehicleEditMake);



        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementView);
        builder.setTitle("Edit Vehicle: "+sVehicleId);
        builder.setPositiveButton("Save",
                (dialog, which) -> {
                    // learned that some older versions of android studio need this empty onClick event to not crash
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
            // extract and convert data from the edit text fields
            sVehicleModel = editTextVehicleModelEdit.getText().toString().trim();
            sVehicleMake = editTextVehicleMakeEdit.getText().toString().trim();
            sVehicleYear = editTextVehicleYearEdit.getText().toString().trim();
            iVehicleId = Integer.parseInt(sVehicleId);

            if (sVehicleYear.length() == 0){
                editTextVehicleModelEdit.setError("Text Can't be empty");
            }
            if (sVehicleModel.length() == 0){
                editTextVehicleModelEdit.setError("Text Can't be empty");
            }
            if (sVehicleMake.length() == 0) {
                editTextVehicleMakeEdit.setError("Text Can't be empty");
            }
            else if(sVehicleId.length() != 0 && sVehicleMake.length() != 0
                    && sVehicleModel.length() != 0 && sVehicleYear.length() != 0) {
                iVehicleYear = Integer.parseInt(sVehicleYear);
                Vehicle vehicle = new Vehicle(iVehicleId, iVehicleYear, sVehicleMake, sVehicleModel);

                // Call the database manager
                FirebaseManager firebaseManager = new FirebaseDatabaseManager(vehicle);
                firebaseManager.editVehicle();

                Toast.makeText(context, "Vehicle Information Updated",Toast.LENGTH_LONG).show();
                dialog.cancel();
            }
        });
    }
}
