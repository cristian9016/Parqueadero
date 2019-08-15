package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import io.reactivex.Observable

interface ProcesarTransaccionInterface {
    fun insertTransaction(transaccion: Transaccion):Observable<Int>
    fun getTransaction(placa: String): Transaccion?
    fun updateTransaction(transaccion: Transaccion,price:Int):Observable<Unit>
    fun validateCountOfPlaces(type:Int):Observable<Boolean>
}