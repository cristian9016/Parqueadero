package co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion

interface DataInterface {
    fun insertData(transaccion: Transaccion)
    fun getData(placa:String): Transaccion
    fun updateData(transaccion: Transaccion)
    fun getNumberOfTransactionsByType(type:Int):Int
}