package co.com.ceiba.adn.estacionamiento.cristian.core.negocio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcesarTransaccionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores.validarPlaca
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import io.reactivex.Observable
import java.util.*

class ProcessTransaction constructor(private val data: TransactionData) : ProcesarTransaccionInterface {

    override fun insertTransaction(transaccion: Transaccion): Observable<Int> =
        Observable.create {
            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (validarPlaca(transaccion.placa, day)) {
                if (getTransaction(transaccion.placa) == null) {
                    data.insertData(transaccion)
                    it.onNext(Constants.INSERT_SUCCESS)
                } else it.onNext(Constants.ERROR_CODE_VECHICLE_EXIST)
            } else it.onNext(Constants.ERROR_CODE_UNAUTHORIZED_PLATE)
            it.onComplete()
        }

    override fun getTransaction(placa: String): Transaccion? =
        data.getData(placa)

    override fun validateCountOfPlaces(type: Int): Observable<Boolean> =
        Observable.fromCallable {
            val count = data.getNumberOfTransactionsByType(type)
            (type == Constants.TYPE_CAR && count < Constants.MAX_CARS) ||
                    (type == Constants.TYPE_MOTORCYCLE && count < Constants.MAX_MOTORCYCLES)
        }

    override fun updateTransaction(transaccion: Transaccion, price: Int): Observable<Unit> =
        Observable.fromCallable {
            transaccion.horaSalida = Date().time
            transaccion.state = false
            transaccion.price = price
            data.updateData(transaccion)
        }


}