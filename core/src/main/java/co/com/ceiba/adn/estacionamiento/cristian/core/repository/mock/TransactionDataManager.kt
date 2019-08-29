package co.com.ceiba.adn.estacionamiento.cristian.core.repository.mock

import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.core.repository.real.TransactionDataManager


class TransactionDataManager:
    TransactionDataManager {

    override fun insertData(transaccion: Transaction) {
    }

    override fun updateData(transaccion: Transaction) {
    }

    override fun getData(placa:String): Transaction? = null

    override fun getNumberOfTransactionsByType(type:Int):Int = 10

}