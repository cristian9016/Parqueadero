package co.com.ceiba.adn.estacionamiento.cristian.core.negocio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.util.EntityToModel
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores.validarPlaca
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

    private fun insertTransaction(transactionModel: TransactionModel): Observable<Int> =
        Observable.create {
            data.insertData(EntityToModel.transactionEntity(transactionModel))
            it.onNext(Constants.INSERT_SUCCESS)
        }

    override fun processTransactionForInsertion(transactionModel: TransactionModel): Observable<Int> =
        validatePlaca(transactionModel.placa)
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
            }
            .flatMap {
                if (it == Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST)
                    insertTransaction(transactionModel)
                else Observable.just(it)
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

    private fun validatePlaca(placa: String): Observable<Int> =
        Observable.create {
            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            val authorized = validarPlaca(placa, day)
            if (authorized)
                it.onNext(Constants.ERROR_CODE_AUTHORIZED_PLATE)
            else it.onNext(Constants.ERROR_CODE_UNAUTHORIZED_PLATE)
        }
}