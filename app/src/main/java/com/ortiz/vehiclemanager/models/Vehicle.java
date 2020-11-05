package com.ortiz.vehiclemanager.models;

/**
 * Vehicle Class
 * 10/31/2020
 * Robert Ortiz
 * This class contains our basic definition of a Vehicle Object
 */
public class Vehicle {
    private int Id;
    private int Year;
    private String Make;
    private String Model;
    public Vehicle(){}
    public Vehicle(int id, int year, String make, String model){
        this.Id = id;
        this.Year = year;
        this.Make = make;
        this.Model = model;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        this.Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

}


