package com.example.farmerama;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MeasurementsTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void measurementsTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.skip), withText("SKIP"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.LoginEmailAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_emailContainer),
                                        0),
                                0)));
        textInputEditText.perform(scrollTo(), replaceText("mariamgd@gmail.com"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.LoginPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_passwordContainer),
                                        0),
                                0)));
        textInputEditText2.perform(scrollTo(), replaceText("password"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.loginButton), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.constraintLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(
                allOf(withId(R.id.latestDataFragment),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.activity_chooser_view_content),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                2),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withId(R.id.constraintLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction navigationMenuItemView2 = onView(
                allOf(withId(R.id.historicalDataFragment),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.activity_chooser_view_content),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                3),
                        isDisplayed()));
        navigationMenuItemView2.perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Humidity"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout_historical),
                                        0),
                                1)));
        tabView.perform(scrollTo(), click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("CO₂"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout_historical),
                                        0),
                                2)));
        tabView2.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Sound"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout_historical),
                                        0),
                                3)));
        tabView3.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
