package co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import io.reactivex.Observable

interface DataInterface {
    fun insertData(transaccion: Transaccion): Observable<Int>
    fun getData(placa:String): Transaccion?
    fun updateData(transaccion: Transaccion,price:Int):Observable<Unit>
    fun getNumberOfTransactionsByType(type:Int):Observable<Boolean>
}