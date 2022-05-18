package com.example.farmerama;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LatestMeasurementFragment {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void latestMeasurementFragment() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.continueAsGuest), withText("Continue as a guest"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        0),
                                4),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction tabView = onView(
                allOf(withContentDescription("Humidity"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                1)));
        tabView.perform(scrollTo(), click());

        ViewInteraction tabView2 = onView(
                allOf(withContentDescription("SPL"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                2)));
        tabView2.perform(scrollTo(), click());

        ViewInteraction tabView3 = onView(
                allOf(withContentDescription("Temperature"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabLayout),
                                        0),
                                0)));
        tabView3.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.latestTemperature_value), withText("20.4"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("20.4")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.latestTemperature_time), withText("14-05-2022 11:10:39"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class))),
                        isDisplayed()));
        editText.check(matches(withText("14-05-2022 11:10:39")));
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
