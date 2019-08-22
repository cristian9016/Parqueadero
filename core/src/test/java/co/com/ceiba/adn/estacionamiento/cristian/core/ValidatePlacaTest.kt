package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ValidatePlacaInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ValidatePlaca
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePlacaTest {

    val validate : ValidatePlacaInterface = ValidatePlaca()
    var value = 0

    @Before
    fun onPrepare(){
        value = 0
    }

    @Test
    fun validatePlacaAuthorized(){
        //arrange

        //act
        validate.validatePlaca("sdf123",1)
            .subscribe {
                value = it
            }
        //assert
        assertEquals(Constants.ERROR_CODE_AUTHORIZED_PLATE,value)
    }

    @Test
    fun validatePlacaUnAuthorized(){
        //arrange

        //act
        validate.validatePlaca("adf123",5)
            .subscribe {
                value = it
            }
        //assert
        assertEquals(Constants.ERROR_CODE_UNAUTHORIZED_PLATE,value)
    }

}