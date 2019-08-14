package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class ProcessTransactionTest {

    lateinit var transaction: Transaccion
    lateinit var dataMock: TransactionData
    lateinit var processTransaction: ProcessTransaction
    @Before
    fun onPrepared() {
        transaction = Transaccion(
            null, "kjh18", 1234L,
            0, Constants.TYPE_CAR, true, 0
        )
        dataMock = Mockito.mock(TransactionData::class.java)
        processTransaction = ProcessTransaction(dataMock)
    }

    @Test
    fun validateInsertTransaction() {
        //arrange
        Mockito.`when`(dataMock.getData("kjh18")).thenReturn(null)
        Mockito.doNothing().`when`(dataMock).insertData(transaction)
        //act
        processTransaction.insertTransaction(transaction)
            .subscribe {
                //assert
                assert(it == Constants.INSERT_SUCCESS)
            }
    }

    @Test
    fun validateSpacesForCars() {
        //arrange
        Mockito.`when`(dataMock.getNumberOfTransactionsByType(Constants.TYPE_CAR)).thenReturn(19)
        //act
        processTransaction.validateCountOfPlaces(Constants.TYPE_CAR)
            .subscribe {
                assert(it)
            }
    }

    @Test
    fun validateNoSpacesForCars() {
        //arrange
        Mockito.`when`(dataMock.getNumberOfTransactionsByType(Constants.TYPE_CAR)).thenReturn(20)
        //act
        processTransaction.validateCountOfPlaces(Constants.TYPE_CAR)
            .subscribe {
                assert(!it)
            }
    }

    @Test
    fun validateSpacesForMotos() {
        //arrange
        Mockito.`when`(dataMock.getNumberOfTransactionsByType(Constants.TYPE_MOTORCYCLE)).thenReturn(9)
        //act
        processTransaction.validateCountOfPlaces(Constants.TYPE_MOTORCYCLE)
            .subscribe {
                assert(it)
            }
    }

    @Test
    fun validateNoSpacesForMotos() {
        //arrange
        Mockito.`when`(dataMock.getNumberOfTransactionsByType(Constants.TYPE_MOTORCYCLE)).thenReturn(10)
        //act
        processTransaction.validateCountOfPlaces(Constants.TYPE_MOTORCYCLE)
            .subscribe {
                assert(!it)
            }
    }

}