package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {

    @Test
    fun validarPlacaA() {
        //arrange
        //act
        val authorizedMonday = Validadores.validarPlaca("Ajn123", 1)
        val authorizedTuesday = Validadores.validarPlaca("ASD098", 2)
        //assert
        assertEquals(true, authorizedMonday && !authorizedTuesday)
    }

    @Test
    fun validarPrecio9HorasCarro() {
        //act
        val precio = Validadores.calcPrice(9, Constants.TYPE_CAR, 0)
        //assert
        assertEquals(Constants.CAR_DAY, precio)
    }
    @Test
    fun validarPrecio9HorasMoto650() {
        //act
        val precio = Validadores.calcPrice(9, Constants.TYPE_MOTORCYCLE, 650)
        //assert
        assertEquals(Constants.MOTORCYCLE_DAY+Constants.EXTRA_MOTO, precio)
    }

}
