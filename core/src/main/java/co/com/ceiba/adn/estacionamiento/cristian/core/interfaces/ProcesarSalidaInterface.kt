package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction
import io.reactivex.Flowable
import io.reactivex.Observable

interface ProcesarSalidaInterface {
    fun insertTransaction(transaccion: Transaction):Observable<Boolean>
    fun getTransaction(placa: String): Flowable<Transaction>
    fun updateTransaction(transaccion: Transaction):Observable<Unit>
}