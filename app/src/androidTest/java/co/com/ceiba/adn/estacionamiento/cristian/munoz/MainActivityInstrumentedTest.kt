package co.com.ceiba.adn.estacionamiento.cristian.munoz

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import co.com.ceiba.adn.estacionamiento.cristian.core.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.di.coreModule
import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.ProcessTransactionImpl
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.main.MainActivity
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.main.MainViewModel
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityInstrumentedTest : KoinTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val processTransaction: ProcessTransaction by inject()
    private val mainViewModel: MainViewModel = get()

    var placa = "qwe123"

    @Test
    fun insertNewCarPlaca() {
        //arrange
        val transaction = processTransaction.getTransaction(placa)
        if (transaction !is Int)
            processTransaction.updateTransaction(transaction as Transaction, 0)

        //act
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnAddIn))
            .perform(click())

        //assert
        onView(withText(R.string.inserted_correctly))
            .check(matches(isDisplayed()))

    }

    @Test
    fun verifyInsertedPlaca() {
        //arrange
        val transaction = processTransaction.getTransaction(placa)
        if (transaction !is Transaction)
            mainViewModel.insertTransaction(placa, Constants.TYPE_CAR, 0).subscribe()

        //act
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnAddIn))
            .perform(click())

        //assert
        onView(withText(R.string.vehicle_exist))
            .check(matches(isDisplayed()))

    }

    @Test
    fun verifyPayment() {
        //arrange
        val transaction = processTransaction.getTransaction(placa)
        if (transaction !is Transaction)
            mainViewModel.insertTransaction(placa, Constants.TYPE_CAR, 0).subscribe()
        //act
        onView(withId(R.id.etPlaca))
            .perform(typeText(placa), closeSoftKeyboard())
        onView(withId(R.id.btnCalcPrice))
            .perform(click())
        onView(withId(R.id.btnPayment))
            .perform(click())
        //assert
        onView(withText(R.string.payment_succeed))
            .check(matches(isDisplayed()))


    }

}
