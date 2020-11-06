package com.ortiz.vehiclemanager.interfaces;

import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/**
 * simple interface class that demonstrates to the clients how to interact with
 * our implementation of Firebase REST API services
 *
 * Robert Ortiz
 * 11/5/2020
 */

public interface FirebaseManager {

    /**
     * This function will edit the vehicle information in our database provided that it passes a
     * valid vehicle to instance of FirebaseDatabaseManager.
     */
    public void editVehicle();

    /**
     * This function will delete the vehicle information in the database provided that it is
     * passed in a valid vehicle id.
     * @param sVehicleId: non-null valid vehicle id
     */
    public void deleteVehicleById(String sVehicleId);


    /**
     * This function will add the vehicle to the database provided that a valid vehicle is sent
     * the instance of the FirebaseDatabaseManager
     */
    public void addVehicle();

    /**
     * This function gets the information from the database and updates the list
     * given that it is provided an ArrayList<String> and a valid ArrayAdapter
     * and a vehicle instance to the FirebaseDatabaseManager instance.
     * @param oVehicleList
     * @param adapter
     */
    public void getDatabaseItems(ArrayList<String> oVehicleList, ArrayAdapter adapter);

    /**
     * This function will get the specific vehicles information given that it is passed
     * a valid vehicle id and a valid view
     * @param sVehicleId: non-null vehicle id value
     * @param view: non-null View
     */
    public void getVehicleById(String sVehicleId, View view);
}
