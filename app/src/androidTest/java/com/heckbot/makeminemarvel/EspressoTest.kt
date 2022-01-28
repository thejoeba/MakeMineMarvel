package com.heckbot.makeminemarvel

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heckbot.makeminemarvel.list.ComicAdapter
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testItemClickAndBack() {
        onView(withId(R.id.rvComics))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ComicAdapter.ComicViewHolder>(
                    0, click()
                )
            )

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(containsString(TestApplication.testComic1.title))))

        Espresso.pressBack()

        onView(withId(R.id.rvComics))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<ComicAdapter.ComicViewHolder>(
                    1, click()
                )
            )

        onView(withId(R.id.tvTitle)).check(matches(withText(containsString(TestApplication.testComic2.title))))

        Espresso.pressBack()

        onView(withId(R.id.rvComics)).check(matches(isDisplayed()))
    }
}