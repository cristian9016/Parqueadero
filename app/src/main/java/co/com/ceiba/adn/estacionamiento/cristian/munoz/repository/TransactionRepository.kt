package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import io.reactivex.Observable

class TransactionRepository constructor(private val data: ProcessTransaction) {

    fun insertTransaction(transactionModel: TransactionModel): Observable<Boolean> =
        data.insertTransaction(EntityToModel.transactionEntity(transactionModel))

    fun getTransaction(placa: String): Observable<TransactionModel> =
        data.getTransaction(placa)
            .toObservable()
            .map {
                EntityToModel.transactionModel(it)
            }

    fun updateTransaction(transactionModel: TransactionModel): Observable<Unit> =
        data.updateTransaction(EntityToModel.transactionEntity(transactionModel))


}