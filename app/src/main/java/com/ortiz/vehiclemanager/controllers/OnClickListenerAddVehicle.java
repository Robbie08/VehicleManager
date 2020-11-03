package com.ortiz.vehiclemanager.controllers;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

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

        // make our layouts accessible to our codebase
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.vehicle_entry_form,null,false);

        // instantiate the four EditText items from our form
        final EditText editTextVehicleMake = (EditText) formElementsView.findViewById(R.id.editTextVehicleMake);
        final EditText editTextVehicleModel = (EditText) formElementsView.findViewById(R.id.editTextVehicleModel);
        final EditText editTextVehicleYear = (EditText) formElementsView.findViewById(R.id.editTextVehicleYear);
        final EditText editTextVehicleId = (EditText) formElementsView.findViewById(R.id.editTextVehicleId);

        // Display a alert dialog box to the user, this will display the entry form to add the vehicle
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Add Vehicle")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String sVehicleMake = "", sVehicleModel = "", sVehicleYear ="", sVehicleId = "";
                                int iVehicleYear = 0, iVehicleId = 0;

                                // Once the user has typed in their information we must collect it
                                sVehicleMake = editTextVehicleMake.getText().toString().trim();
                                sVehicleModel = editTextVehicleModel.getText().toString().trim();
                                sVehicleYear = editTextVehicleYear.getText().toString().trim();
                                sVehicleId = editTextVehicleId.getText().toString().trim();




                                Vehicle vehicle = new Vehicle(iVehicleId,iVehicleYear,sVehicleMake,sVehicleModel);
                                DatabaseReference oVehicleReference = FirebaseDatabase.getInstance().getReference().child("Vehicles");
                                boolean stringsIsEmpty = sVehicleId.isEmpty() || sVehicleYear.isEmpty() || sVehicleMake.isEmpty() || sVehicleModel.isEmpty();
                                boolean stringsIsZero = sVehicleMake.length() == 0 || sVehicleModel.length() == 0 || sVehicleYear.length() == 0 || sVehicleId.length() == 0;
                                if(!stringsIsEmpty && !stringsIsZero){
                                    // we need to covert year and id to int so that we can create our Vehicle object
                                    iVehicleYear = Integer.parseInt(sVehicleYear);
                                    iVehicleId = Integer.parseInt(sVehicleId);

                                    oVehicleReference.child(sVehicleId).child("Make").setValue(sVehicleMake);
                                    oVehicleReference.child(sVehicleId).child("Model").setValue(sVehicleModel);
                                    oVehicleReference.child(sVehicleId).child("Year").setValue(iVehicleYear);
                                    oVehicleReference.child(sVehicleId).child("Id").setValue(iVehicleId);

                                    // if successful then we will display a toast, else display failure
                                    Toast.makeText(context, "Vehicle Added Successfully",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    // if successful then we will display a toast, else display failure
                                    Toast.makeText(context, "Error: Vehicle was not added",Toast.LENGTH_LONG).show();
                                }

                                dialog.cancel();
                            }
                        }).show();
    }
}