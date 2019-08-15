package co.com.ceiba.adn.estacionamiento.cristian.munoz

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityInstrumentedTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    var placa = "PLK000"

    @Test
    fun aInsertNewCarPlaca() {
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa))
        onView(withId(R.id.btnAddIn))
            .perform(click())
        onView(withText(R.string.inserted_correctly))
            .check(matches(isDisplayed()))
    }

    @Test
    fun bVerifyInsertedPlaca() {
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa))
        onView(withId(R.id.btnAddIn))
            .perform(click())
        onView(withText(R.string.vehicle_exist))
            .check(matches(isDisplayed()))
    }

    @Test
    fun cVerifyPriceToPay() {
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnCalcPrice))
            .perform(click())
        onView(withId(R.id.tvValueToPay))
            .check(matches(withText(Constants.CAR_HOUR.toString())))
    }

}
