package com.ortiz.vehiclemanager;

import com.ortiz.vehiclemanager.models.Vehicle;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    /**
     * Test weather our object is created correctly within the program
     */
    Vehicle sampleVehicle = new Vehicle(11232,2014,"Mazda","Speed3");
    @Test
    public void vehicleIdIsCreated(){assertEquals(11232, sampleVehicle.getId());}
    @Test
    public void vehicleYearIsCreated(){assertEquals(2014,sampleVehicle.getYear());}
    @Test
    public void vehicleMakeIsCreated(){assertEquals("Mazda",sampleVehicle.getMake());}
    @Test
    public void vehicleModelIsCreated(){assertEquals("Speed3",sampleVehicle.getModel());}

}