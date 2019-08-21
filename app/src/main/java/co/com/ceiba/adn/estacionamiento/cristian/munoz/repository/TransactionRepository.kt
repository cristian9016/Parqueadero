package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import io.reactivex.Maybe
import io.reactivex.Observable

class TransactionRepository constructor(private val data: ProcessTransactionInterface) {

    fun insertTransaction(transactionModel: TransactionModel): Observable<Int> =
        data.processTransactionForInsertion(transactionModel)

    fun getTransaction(placa: String): Observable<Any> =
        data.getTransaction(placa)

    fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit> =
        data.updateTransaction(transactionModel, price)

    fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean> =
        data.checkNumberOfTransactionsByType(type)


}