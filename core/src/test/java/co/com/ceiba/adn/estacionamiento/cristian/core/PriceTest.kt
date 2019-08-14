package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
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
        //act
        price.price(inDate, outDate, Constants.TYPE_CAR, 0)
            .subscribe {
                assert(it == 4000)
            }
    }

    @Test
    fun validatePriceMoto4Hours() {
        //act
        price.price(inDate, outDate, Constants.TYPE_MOTORCYCLE, 0)
            .subscribe {
                assert(it == 2000)
            }
    }

    @Test
    fun validatePriceMoto4Hours650cc() {
        //act
        price.price(inDate, outDate, Constants.TYPE_MOTORCYCLE, 650)
            .subscribe {
                assert(it == 4000)
            }
    }

}