package co.com.ceiba.adn.estacionamiento.cristian.core.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction


interface TransactionDataManager {

    fun insertData(transaccion: Transaction)

    fun getData(placa:String): Transaction?

    fun updateData(transaccion: Transaction)

    fun getNumberOfTransactionsByType(type:Int):Int

}