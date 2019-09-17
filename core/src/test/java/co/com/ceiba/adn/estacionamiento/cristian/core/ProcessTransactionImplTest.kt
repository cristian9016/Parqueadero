package co.com.ceiba.adn.estacionamiento.cristian.core

import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.ProcessTransactionImpl
import co.com.ceiba.adn.estacionamiento.cristian.core.mocks.TransactionDataManagerImplMock
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.core.repository.TransactionDataManager
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.stopKoin


class ProcessTransactionImplTest {

    lateinit var processTransaction : ProcessTransactionImpl
    val transactionDataManager : TransactionDataManager = TransactionDataManagerImplMock()
    val transaction = Transaction(null,"kjh123",1234,0,
        Constants.TYPE_CAR,true,0)

    val day = 5

    @Before
    fun onPrepared(){
        processTransaction = ProcessTransactionImpl(transactionDataManager)
    }

    @Test
    fun validateTransactionForInsertion(){
        //arrange

        //act
        val result = processTransaction.validateTransactionforInsertion(transaction,day)
        //assert
        assertEquals(Constants.INSERT_SUCCESS,result)
    }

    @After
    fun onFinished(){
        stopKoin()
    }

}