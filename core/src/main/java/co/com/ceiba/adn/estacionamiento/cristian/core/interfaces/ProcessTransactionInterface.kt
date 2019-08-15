package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import io.reactivex.Maybe
import io.reactivex.Observable

interface ProcessTransactionInterface {
    fun insertTransaction(transactionModel: TransactionModel): Observable<Int>

    fun getTransaction(placa: String): Maybe<TransactionModel>

    fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit>

    fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean>
}