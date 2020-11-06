package com.ortiz.vehiclemanager.models;

import com.ortiz.vehiclemanager.interfaces.Utils;

/**
 * A class that contains some useful utilities for our client
 */
public class MyUtils implements Utils {
    public MyUtils(){}

    public int generateUniqueId(){
        int uniqueId = 0;
        uniqueId = (int) (System.currentTimeMillis()/1000);
        uniqueId -= 100000000;
        return uniqueId;
    }
}
