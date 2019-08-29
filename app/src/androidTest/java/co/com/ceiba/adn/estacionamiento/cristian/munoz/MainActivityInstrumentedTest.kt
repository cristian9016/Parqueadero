package co.com.ceiba.adn.estacionamiento.cristian.munoz

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.main.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    var placa = "qwe123"

    @Test
    fun insertNewCarPlaca() {
        //arrange
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnAddIn))
            .perform(click())
        try {
            onView(withText(R.string.inserted_correctly))
                .check(matches(isDisplayed()))
        } catch (error: NoMatchingViewException) {
            onView(withText("OK"))
                .perform(click())
            onView(withId(R.id.btnCalcPrice))
                .perform(click())
            onView(withId(R.id.btnPayment))
                .perform(click())
            onView(withText("OK"))
                .perform(click())
            onView(withId(R.id.btnAddIn))
                .perform(click())
            onView(withText(R.string.inserted_correctly))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun verifyInsertedPlaca() {
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnAddIn))
            .perform(click())
        try {
            onView(withText(R.string.vehicle_exist))
                .check(matches(isDisplayed()))
        } catch (error: NoMatchingViewException) {
            onView(withText("OK"))
                .perform(click())
            onView(withId(R.id.btnAddIn))
                .perform(click())
            onView(withText(R.string.vehicle_exist))
                .check(matches(isDisplayed()))
        }

    }

    @Test
    fun verifyPayment() {
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnCalcPrice))
            .perform(click())
        try {
            onView(withId(R.id.btnPayment))
                .perform(click())
            onView(withText(R.string.payment_succeed))
                .check(matches(isDisplayed()))
        }catch (error:NoMatchingViewException){
            onView(withText("OK"))
                .perform(click())
            onView(withId(R.id.btnAddIn))
                .perform(click())
            onView(withText("OK"))
                .perform(click())
            onView(withId(R.id.btnCalcPrice))
                .perform(click())
            onView(withId(R.id.btnPayment))
                .perform(click())
            onView(withText(R.string.payment_succeed))
                .check(matches(isDisplayed()))
        }

    }

}
