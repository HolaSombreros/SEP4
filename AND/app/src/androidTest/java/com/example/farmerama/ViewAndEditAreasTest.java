package com.example.farmerama;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.example.farmerama.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ViewAndEditAreasTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void viewAndEditAreasTest() {
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
allOf(withId(R.id.areasFragment),
childAtPosition(
allOf(withId(androidx.appcompat.R.id.activity_chooser_view_content),
childAtPosition(
withId(R.id.nav_view),
0)),
4),
isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction recyclerView = onView(
allOf(withId(R.id.areas_recycleView),
childAtPosition(
withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
0)));


        ViewInteraction materialButton3 = onView(
allOf(withId(R.id.addArea_createButton), withText("Save"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.ScrollView")),
0),
6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction imageButton = onView(
allOf(withId(R.id.areas_fab),
withParent(withParent(withId(R.id.fragment))),
isDisplayed()));
        imageButton.check(matches(isDisplayed()));
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
