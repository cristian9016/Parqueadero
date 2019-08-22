package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.mock_repository.DataInterfaceMock
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class ProcessTransactionTest {

    val dataMock = DataInterfaceMock()
    val processTransaction = ProcessTransaction(dataMock)
    val transaction = TransactionModel(null,"kjh123",1234,0,Constants.TYPE_CAR,true,0)
    var value = -1
    var state = false

    @Before
    fun onPrepared() {
        value = -1
        state = false
    }

    @Test
    fun validateInsertion(){
        //arrange
        //act
        processTransaction.insertTransaction(transaction)
            .subscribe {
                value = it
            }
        //assert
        assertEquals(Constants.INSERT_SUCCESS,value)
    }

    @Test
    fun validateGetNullTransaction() {
        //arrange

        //act
        processTransaction.getTransaction("kjh18")
            .subscribe {
                value = it as Int
            }
        //assert
        assertEquals(Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST, value)
    }

    @Test
    fun validateNoSpacesForMotos() {
        //arrange

        //act
        processTransaction.checkNumberOfTransactionsByType(Constants.TYPE_MOTORCYCLE)
            .subscribe {
                state = it
            }
        //assert
        assertEquals(false, state)
    }
}