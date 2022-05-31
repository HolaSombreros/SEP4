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
public class RegisterUserTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void registerUserTest() {
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
                allOf(withId(R.id.registerFragment),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.activity_chooser_view_content),
                                        childAtPosition(
                                                withId(R.id.nav_view),
                                                0)),
                                8),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.RegisterEmailAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_emailContainer),
                                        0),
                                0)));
        textInputEditText3.perform(scrollTo(), replaceText("1234@1234"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.RegisterFirstName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.firstNameContainer),
                                        0),
                                0)));
        textInputEditText4.perform(scrollTo(), replaceText("Rasmus"), closeSoftKeyboard());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.RegisterLastName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lastNameContainer),
                                        0),
                                0)));
        textInputEditText5.perform(scrollTo(), replaceText("Malmquist"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.RegisterPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_passwordContainer),
                                        0),
                                0)));
        textInputEditText6.perform(scrollTo(), replaceText("12345"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.RegisterEmailAddress), withText("1234@1234"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_emailContainer),
                                        0),
                                0)));
        textInputEditText7.perform(scrollTo(), replaceText("1234@1234.com"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.RegisterEmailAddress), withText("1234@1234.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_emailContainer),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction checkableImageButton = onView(
                allOf(withId(androidx.appcompat.R.id.search_src_text), withContentDescription("Show password"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        checkableImageButton.perform(click());

        ViewInteraction textInputEditText9 = onView(
                allOf(withId(R.id.RegisterPassword), withText("12345"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_passwordContainer),
                                        0),
                                0)));
        textInputEditText9.perform(scrollTo(), replaceText("123456"));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.RegisterPassword), withText("123456"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_passwordContainer),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText10.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.registerButton), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        materialButton5.perform(scrollTo(), click());
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
