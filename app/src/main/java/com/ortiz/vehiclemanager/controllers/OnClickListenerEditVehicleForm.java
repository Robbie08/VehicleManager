package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.ortiz.vehiclemanager.R;

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
    public OnClickListenerEditVehicleForm(View viewById) {
        this.viewItem = viewById;
        this.context = viewById.getRootView().getContext();
        onClick(viewById);
    }


    @Override
    public void onClick(View v) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementView = inflater.inflate(R.layout.vehicle_edit_form,null,false);
        new AlertDialog.Builder(context)
                .setView(formElementView)
                .setTitle("Enter New Information")
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
    }
}
