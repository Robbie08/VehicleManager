package com.ortiz.vehiclemanager.controllers;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ortiz.vehiclemanager.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;

    /**
     * Set up our virtual environment
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        mainActivity = mActivityTestRule.getActivity();
    }

    /**
     * Test to ensure that our application does not crash when user clicks on it.
     * Then we will type in some text into EditText field and we will purposely leave one
     * blank to see if it does not submit the form.
     */
    @Test
    public void testAddButtonClick(){
        Espresso.onView(withId(R.id.buttonAddVehicle)).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleMake)).perform(typeText("Toyota"));
        Espresso.onView(withId(R.id.editTextVehicleModel)).perform(typeText("4Runner"));
        Espresso.onView(withId(R.id.editTextVehicleId)).perform(typeText(""));
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(typeText("2015"));
        Espresso.onView(withText("ADD")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        //Espresso.pressBack();
    }

    @Test
    public void testDeleteButtonClick(){
        Espresso.onView(withId(R.id.buttonDeleteVehicle)).perform(click());
        Espresso.pressBack();
    }


    @Test
    public void testEditButtonClick(){
        Espresso.onView(withId(R.id.buttonEditVehicle)).perform(click());
        Espresso.pressBack();
    }

    @Test
    public void testGetButtonClick(){
        Espresso.onView(withId(R.id.buttonGetVehicle)).perform(click());
        Espresso.pressBack();
    }



    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}