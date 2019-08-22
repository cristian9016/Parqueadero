package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ValidatePlacaInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import io.reactivex.Observable
import java.util.*

class TransactionRepository constructor(
    private val data: ProcessTransactionInterface,
    private val validate: ValidatePlacaInterface
) {

    fun validateTransactionforInsertion(transactionModel: TransactionModel): Observable<Int> =
        validate.validatePlaca(
            transactionModel.placa,
            Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        )
            .flatMap {
                if (it == Constants.ERROR_CODE_AUTHORIZED_PLATE)
                    getTransaction(transactionModel.placa)
                else Observable.create<Int> { emmiter -> emmiter.onNext(Constants.ERROR_CODE_UNAUTHORIZED_PLATE) }
            }
            .map {
                when (it) {
                    is TransactionModel -> Constants.ERROR_CODE_VECHICLE_EXIST
                    is Int -> it
                    else -> Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST
                }
            }.flatMap {
                if (it == Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST)
                    data.insertTransaction(transactionModel)
                else Observable.just(it)
            }


    fun getTransaction(placa: String): Observable<Any> =
        data.getTransaction(placa)

    fun updateTransaction(transactionModel: TransactionModel, price: Int): Observable<Unit> =
        data.updateTransaction(transactionModel, price)

    fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean> =
        data.checkNumberOfTransactionsByType(type)


}