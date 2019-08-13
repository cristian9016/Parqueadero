package co.com.ceiba.adn.estacionamiento.cristian.core.negocio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcesarSalidaInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores.validarPlaca
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*

class ProcessTransaction constructor(private val data: TransactionData) : ProcesarSalidaInterface {

    override fun insertTransaction(transaccion: Transaction): Observable<Boolean> =
        Observable.create {
            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
            if (validarPlaca(transaccion.placa, day)) {
                data.insertData(transaccion)
                it.onNext(true)
            } else it.onComplete()
        }

    override fun getTransaction(placa: String): Flowable<Transaction> =
        data.getData(placa)


    override fun updateTransaction(transaccion: Transaction): Observable<Unit> =
        Observable.create {
            it.onNext(data.updateData(transaccion))
        }

}