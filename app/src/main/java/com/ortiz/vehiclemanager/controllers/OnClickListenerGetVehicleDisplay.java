package com.ortiz.vehiclemanager.controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ortiz.vehiclemanager.R;
import com.ortiz.vehiclemanager.models.Vehicle;

public class OnClickListenerGetVehicleDisplay{
    Vehicle vehicle;
    String sVehicleId;
    View formView;
    Context context;
    public OnClickListenerGetVehicleDisplay(View formElementView, String sVehicleId, Vehicle vehicle) {
        this.sVehicleId = sVehicleId;
        this.formView = formElementView;
        this.vehicle = vehicle;
        context = this.formView.getRootView().getContext();
        onClick();
    }


    public void onClick(){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementView = inflater.inflate(R.layout.vehicle_display_data,null,false);

        TextView textViewMake =  formElementView.getRootView().findViewById(R.id.textViewVehicleShowMake);
        TextView textViewModel = formElementView.getRootView().findViewById(R.id.textViewVehicleShowModel);
        TextView textViewYear =  formElementView.getRootView().findViewById(R.id.textViewVehicleShowYear);
        TextView textViewId =  formElementView.getRootView().findViewById(R.id.textViewVehicleShowId);

        textViewMake.setText(vehicle.getMake());
        textViewModel.setText(vehicle.getModel());
        textViewYear.setText(Integer.toString(vehicle.getYear()));
        textViewId.setText(Integer.toString(vehicle.getId()));

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(formElementView);
        builder.setTitle("Vehicle Data: " +sVehicleId);
        builder.setPositiveButton("Close",
                (dialog, which) -> {
                    // learned that some older versions of android studio need this empty onClick event to not crash
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {

                dialog.cancel();
        });
    }
}
