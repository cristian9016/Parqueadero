package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import io.reactivex.Observable

interface ProcessTransactionInterface {

    //verificar placa, verificar que no exista e insertar
    fun processTransactionForInsertion(transactionModel: TransactionModel): Observable<Int>

    fun getTransaction(placa: String): Observable<Any>

    fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit>

    fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean>
}