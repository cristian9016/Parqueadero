package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class TransactionRepository constructor(private val data: ProcessTransaction) {

    fun insertTransaction(transactionModel: TransactionModel): Observable<Int> =
        data.insertTransaction(EntityToModel.transactionEntity(transactionModel))

    fun getTransaction(placa: String): Maybe<TransactionModel> =
        Maybe.create<Transaccion> {
            val transaction = data.getTransaction(placa)
            if (transaction != null)
                it.onSuccess(transaction)
            else it.onError(Throwable("${Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST}"))
        }
            .map {
                EntityToModel.transactionModel(it)
            }

    fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit> =
        data.updateTransaction(EntityToModel.transactionEntity(transactionModel), price)

    fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean> =
        data.validateCountOfPlaces(type)


}