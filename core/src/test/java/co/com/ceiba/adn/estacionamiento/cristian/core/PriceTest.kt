package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.PriceImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PriceTest {

    lateinit var price: PriceImpl
    val inDate = 0L
    val outDate = 14400000L //4 horas


    @Before
    fun onPrepared() {
        price = PriceImpl()
    }

    @Test
    fun validatePriceCar4Hours() {
        //act
        var result = price.getPrice(inDate, outDate, Constants.TYPE_CAR, 0)
        //ssert
        assertEquals(4000, result)
    }

    @Test
    fun validatePriceMoto4Hours() {
        //act
        val result = price.getPrice(inDate, outDate, Constants.TYPE_MOTORCYCLE, 0)
        //assert
        assertEquals(2000, result)
    }

    @Test
    fun validatePriceMoto4Hours650cc() {
        //act
        val result = price.getPrice(inDate, outDate, Constants.TYPE_MOTORCYCLE, 650)
        //asssert
        assertEquals(4000, result)
    }

}