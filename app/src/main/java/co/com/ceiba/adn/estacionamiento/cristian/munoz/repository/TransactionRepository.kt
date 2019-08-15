package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import io.reactivex.Maybe
import io.reactivex.Observable

class TransactionRepository constructor(private val data: ProcessTransaction) : ProcessTransactionInterface {

    override fun insertTransaction(transactionModel: TransactionModel): Observable<Int> =
        data.insertData(EntityToModel.transactionEntity(transactionModel))

    override fun getTransaction(placa: String): Maybe<TransactionModel> =
        Maybe.create<Transaccion> {
            val transaction = data.getData(placa)
            if (transaction != null)
                it.onSuccess(transaction)
            else it.onError(Throwable("${Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST}"))
        }
            .map {
                EntityToModel.transactionModel(it)
            }

    override fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit> =
        data.updateData(EntityToModel.transactionEntity(transactionModel), price)

    override fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean> =
        data.getNumberOfTransactionsByType(type)


}