package org.d3if3135.mobpro1

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("org.d3if3135.mobpro1", appContext.packageName)
    }

    @Test
    fun test_activityStart() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(org.d3if3135.mobpro1.R.id.buttonHitung)).perform(click())
    }

    @Test
    fun test_searchButtonFunctionality() {
        onView(withId(org.d3if3135.mobpro1.R.id.buttonHitung)).perform(click());
    }
}