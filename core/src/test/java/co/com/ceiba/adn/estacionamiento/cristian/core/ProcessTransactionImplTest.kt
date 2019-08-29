package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.repository.mock.TransactionDataManager
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.ProcessTransactionImpl
import junit.framework.Assert.assertEquals
import org.junit.Test


class ProcessTransactionImplTest {

    val dataMock =
        TransactionDataManager()

    val processTransaction = ProcessTransactionImpl(dataMock)

    val transaction = Transaction(null,"kjh123",1234,0,
        Constants.TYPE_CAR,true,0)

    val day = 5

    @Test
    fun validateTransactionForInsertion(){
        //act
        val result = processTransaction.validateTransactionforInsertion(transaction,day)
        //assert
        assertEquals(Constants.INSERT_SUCCESS,result)
    }
}