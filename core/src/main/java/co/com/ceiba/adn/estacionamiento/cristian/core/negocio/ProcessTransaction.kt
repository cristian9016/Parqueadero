package co.com.ceiba.adn.estacionamiento.cristian.core.negocio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface
import io.reactivex.Observable
import java.util.*

class ProcessTransaction constructor(private val data: DataInterface) :
    ProcessTransactionInterface {

    //devuelve el objeto si existe o el codigo de error si no existe
    override fun getTransaction(placa: String): Observable<Any> =
        Observable.create<Any> {
            val transactionModel = data.getData(placa)
            if (transactionModel != null)
                it.onNext(EntityToModel.transactionModel(transactionModel))
            else it.onNext(Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST)
        }

    override fun insertTransaction(transactionModel: TransactionModel) : Observable<Int> {
        data.insertData(EntityToModel.transactionEntity(transactionModel))
        return Observable.just(Constants.INSERT_SUCCESS)
    }


    override fun updateTransaction(
        transactionModel: TransactionModel,
        price: Int
    ): Observable<Unit> =
        Observable.fromCallable {
            transactionModel.horaSalida = Date().time
            transactionModel.state = false
            transactionModel.price = price
            data.updateData(EntityToModel.transactionEntity(transactionModel))
        }

    override fun checkNumberOfTransactionsByType(type: Int): Observable<Boolean> =
        Observable.fromCallable {
            val count = data.getNumberOfTransactionsByType(type)
            (type == Constants.TYPE_CAR && count < Constants.MAX_CARS) ||
                    (type == Constants.TYPE_MOTORCYCLE && count < Constants.MAX_MOTORCYCLES)
        }
}