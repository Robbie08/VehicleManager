package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ortiz.vehiclemanager.R;
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
    String sVehicleId = "", sVehicleModel="", sVehicleMake="", sVehicleYear="";
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

        DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(sVehicleId);

        new AlertDialog.Builder(context)
                .setView(formElementView)
                .setTitle("Edit Vehicle: "+sVehicleId)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // extract and convert data from the edit text fields
                                sVehicleModel = editTextVehicleModelEdit.getText().toString().trim();
                                sVehicleMake = editTextVehicleMakeEdit.getText().toString().trim();
                                sVehicleYear = editTextVehicleYearEdit.getText().toString().trim();
                                iVehicleId = Integer.parseInt(sVehicleId);

                                if(sVehicleYear.length() != 0 || !sVehicleYear.isEmpty()){
                                    iVehicleYear = Integer.parseInt(sVehicleYear);
                                }

                                Vehicle vehicle = new Vehicle(iVehicleId, iVehicleYear, sVehicleMake, sVehicleModel);
                                oVehicleReference.child("Make").setValue(vehicle.getMake());
                                oVehicleReference.child("Model").setValue(vehicle.getModel());
                                oVehicleReference.child("Year").setValue(vehicle.getYear());
                                dialog.cancel();
                            }
                        }).show();
    }
}
