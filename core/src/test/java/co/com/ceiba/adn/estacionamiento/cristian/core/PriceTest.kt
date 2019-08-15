package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PriceTest {

    lateinit var price: Price
    val inDate = 0L
    val outDate = 14400000L //4 horas


    @Before
    fun onPrepared() {
        price = Price()
    }

    @Test
    fun validatePriceCar4Hours() {
        var result = 0
        //act
        price.getPrice(inDate, outDate, Constants.TYPE_CAR, 0)
            .subscribe {
                result = it
            }
        assertEquals(4000, result)
    }

    @Test
    fun validatePriceMoto4Hours() {
        var result = 0
        //act
        price.getPrice(inDate, outDate, Constants.TYPE_MOTORCYCLE, 0)
            .subscribe {
                result = it
            }
        assertEquals(2000, result)
    }

    @Test
    fun validatePriceMoto4Hours650cc() {
        var result = 0
        //act
        price.getPrice(inDate, outDate, Constants.TYPE_MOTORCYCLE, 650)
            .subscribe {
                result = it
            }
        assertEquals(4000, result)
    }

}