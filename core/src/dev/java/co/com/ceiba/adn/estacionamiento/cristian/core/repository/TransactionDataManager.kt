package co.com.ceiba.adn.estacionamiento.cristian.core.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction


interface TransactionDataManager{

    fun insertData(transaccion: Transaction) {
    }

    fun updateData(transaccion: Transaction) {
    }

    fun getData(placa:String): Transaction? = null

    fun getNumberOfTransactionsByType(type:Int):Int = 10

}