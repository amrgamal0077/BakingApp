package com.example.amrgamal.bakingapp;


import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by AmrGamal on 07/04/2019.
 */
@RunWith(AndroidJUnit4.class)
public class MainTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initValidString() {
        mIdlingResource = mActivity.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }


    @Test
    public void clickRecyclerViewItem_OpensMain2Activity() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.linear))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.ingredients)).check(matches(isDisplayed()));

    }

    @After
    public void unRegister() {
        if (mIdlingResource != null)
            IdlingRegistry.getInstance().unregister(mIdlingResource);
    }
}
