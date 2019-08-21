package co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import io.reactivex.Observable

interface DataInterface {
    fun insertData(transaccion: Transaccion)
    fun getData(placa:String): Transaccion?
    fun updateData(transaccion: Transaccion)
    fun getNumberOfTransactionsByType(type:Int):Int
}