package com.guillaumegonnet.scorekeeperV2;


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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

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

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.play_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_host_fragment),
                                        1),
                                8),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.increaseTeam1By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.scoreGame1), withText("1"),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("1")));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.increaseTeam1By7), withText("7"),
                        childAtPosition(
                                allOf(withId(R.id.plus_team1),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                4)),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.scoreGame1), withText("8"),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView3.check(matches(withText("8")));

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withId(R.id.increaseTeam1By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.endGame_btn), withText("End Game"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.scoreMatch1), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("1")));

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withId(R.id.increaseTeam1By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

        ViewInteraction appCompatImageButton5 = onView(
                allOf(withId(R.id.increaseTeam2By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton5.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.increaseTeam2By5), withText("5"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        4),
                                3),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.scoreGame2), withText("6"),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("6")));

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.endGame_btn), withText("End Game"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.scoreMatch2), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("1")));

        ViewInteraction appCompatImageButton6 = onView(
                allOf(withId(R.id.increaseTeam2By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton6.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.endGame_btn), withText("End Game"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatImageButton7 = onView(
                allOf(withId(R.id.increaseTeam2By1),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam2),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatImageButton7.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.endGame_btn), withText("End Game"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatButton6.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class), isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.scoreMatch1), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("0")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.scoreMatch2), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("0")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.scoreGame1), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam1),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("0")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.scoreGame2), withText("0"),
                        childAtPosition(
                                allOf(withId(R.id.gameTeam2),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                                2)),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText("0")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.remaining_points), withText("Remaining points : 99"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("Remaining points : 99")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.balls_scored), withText("No Balls scored yet"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                2),
                        isDisplayed()));
        textView12.check(matches(withText("No Balls scored yet")));
    }
}
