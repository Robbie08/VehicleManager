package com.ortiz.vehiclemanager.controllers;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ortiz.vehiclemanager.R;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * This is the test class for our Main Activity. In the main activity we must test, the Add, Edit,
 * Delete, and Get buttons. We should also test for interactions with the AlertDialog forms, to ensure
 * that the application does not crash, the user doesn't type in any out of bounds/null values, and that
 * the application sends us to the correct location.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private MainActivity mainActivity = null;
    private String dummyMake = "Toyota", dummyModel = "4Runner-Test", dummyYear = "2001", dummyId = "1010";


    /**
     * Set up our virtual environment
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        mainActivity = mActivityTestRule.getActivity();
    }

    /**
     * Test the Add Vehicle Alert Dialog that prompts the user to enter a Make, Model, Year, and Id.
     *
     * This test will enter an empty text and it should 1) not crash
     * and 2) not send null/empty information null (3) send data to the next Alert Dialog iff the
     * the user has typed in some id into the edit text box.
     */
    @Test
    public void test1_testAddButtonClickIfFieldLeftBlank(){
        Espresso.onView(withId(R.id.buttonAddVehicle)).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleMake)).perform(typeText(dummyMake));
        Espresso.onView(withId(R.id.editTextVehicleModel)).perform(typeText(dummyModel));
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(typeText(""));
        Espresso.onView(withText("ADD")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }

    /**
     * This function tests the Add Vehicle form. The test case should 1) not crash when the year input
     * is <1950 and >2050. (2) The correct deterrent should prompt the user that the year is not valid.
     */
    @Test
    public void test1_1testAddFormEnterNotValidYear(){
        Espresso.onView(withId(R.id.buttonAddVehicle)).perform(click());
        Espresso.onView(withId(R.id.editTextVehicleMake)).perform(typeText(dummyMake));
        Espresso.onView(withId(R.id.editTextVehicleModel)).perform(typeText(dummyModel));
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(typeText("1949"));

        Espresso.onView(withText("ADD")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(clearText());
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(typeText("2051"));
        Espresso.onView(withText("ADD")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    /**
     * Test the Delete Vehicle Alert Dialog that prompts the user to enter a vehicle Id
     *
     * This test will enter an empty text and it should 1) not crash
     * and 2) not send null/empty information null (3) send data to the next Alert Dialog iff the
     * the user has typed in some id into the edit text box.
     */
    @Test
    public void test2_testDeleteButtonClickIfFieldLeftBlank(){
        // click on Delete Vehicle button
        Espresso.onView(withId(R.id.buttonDeleteVehicle)).perform(click());

        // the following code tests the form
        Espresso.onView(withId(R.id.editTextVehicleDeleteId)).perform(typeText(""));
        Espresso.onView(withText("DELETE")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }


    /**
     * Test the Edit Vehicle Alert Dialog that prompts the user to enter a Vehicle Id
     *
     * This test will enter an empty text and it should 1) not crash
     * and 2) not send null/empty information null (3) send data to the next Alert Dialog iff the
     * the user has typed in some id into the edit text box.
     */
    @Test
    public void test3_testEditVehicleByIdFormIfLeftEmpty(){
        Espresso.onView(withId(R.id.buttonEditVehicle)).perform(click());
        Espresso.onView(withId(R.id.editTextVehicleEditById)).perform(typeText(""));
        Espresso.onView(withText("EDIT")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleEditById)).perform(typeText(dummyId));
        Espresso.onView(withText("EDIT")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    /**
     * Test the Get Vehicle Alert Dialog that prompts the user to enter an Id
     *
     * This test will enter an empty text and it should 1) not crash
     * and 2) not send null/empty information null (3) send data to the next Alert Dialog iff the
     * the user has typed in some id into the edit text box.
     */
    @Test
    public void test5_1testGetVehicleByIdFormIfLeftEmpty(){
        Espresso.onView(withId(R.id.buttonGetVehicle)).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleGetById)).perform(typeText(""));
        Espresso.onView(withText("GET")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleGetById)).perform(typeText(dummyId));
        Espresso.onView(withText("GET")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void test5_testGetVehicleDisplayValidation(){
        Espresso.onView(withId(R.id.buttonGetVehicle)).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleGetById)).perform(typeText(dummyId));
        Espresso.onView(withText("GET")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        Espresso.onView(withId(R.id.textViewVehicleShowMake)).check(matches(withText(dummyMake)));
        Espresso.onView(withId(R.id.textViewVehicleShowModel)).check(matches(withText(dummyModel)));
        Espresso.onView(withId(R.id.textViewVehicleShowYear)).check(matches(withText(dummyYear)));
        Espresso.onView(withId(R.id.textViewVehicleShowId)).check(matches(withText(dummyId)));
    }

    /**
     * This will test if the user was able to send a vehicle to the database
     */
    @Test
    public void test4_testAddVehicleToDatabase(){
        Espresso.onView(withId(R.id.buttonAddVehicle)).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleMake)).perform(typeText(dummyMake));
        Espresso.onView(withId(R.id.editTextVehicleModel)).perform(typeText(dummyModel));
        Espresso.onView(withId(R.id.editTextVehicleYear)).perform(typeText(dummyYear));
        Espresso.onView(withText("ADD")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    /**
     * This will test if the user was able to edit Vehicle from the database
     */
    @Test
    public void test6testEditVehicleInDatabase(){
        Espresso.onView(withId(R.id.buttonEditVehicle)).perform(click());
        Espresso.onView(withId(R.id.editTextVehicleEditById)).perform(typeText(dummyId));
        Espresso.onView(withText("EDIT")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        Espresso.onView(withId(R.id.editTextVehicleEditMake)).perform(typeText(dummyMake));
        Espresso.onView(withId(R.id.editTextVehicleEditModel)).perform(typeText(dummyModel));
        Espresso.onView(withId(R.id.editTextVehicleEditYear)).perform(typeText(dummyYear));
        Espresso.onView(withText("SAVE")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }


    /**
     * This will test if the user was able to delete Vehicle from the database
     */
    @Test
    public void test7_testDeleteVehicleByIdInDatabase(){
        // click on Delete Vehicle button
        Espresso.onView(withId(R.id.buttonDeleteVehicle)).perform(click());

        // the following code tests the form
        Espresso.onView(withId(R.id.editTextVehicleDeleteId)).perform(typeText(dummyId));
        Espresso.onView(withText("DELETE")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}