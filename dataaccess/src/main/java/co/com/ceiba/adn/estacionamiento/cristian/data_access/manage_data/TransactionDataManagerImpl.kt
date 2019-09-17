package co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data

import co.com.ceiba.adn.estacionamiento.cristian.core.repository.TransactionDataManager
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.mapper.toTransaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.mapper.toTransactionData

class TransactionDataManagerImpl(private val dao: TransactionDao) :
    TransactionDataManager {

    override fun getData(placa: String): Transaction? =
        dao.getTransaction(placa)?.toTransaction()

    override fun insertData(transaccion: Transaction) =
        dao.insertTransaction(transaccion.toTransactionData())

    override fun getNumberOfTransactionsByType(type: Int): Int =
        dao.validateNumberOfVehicles(type)

    override fun updateData(transaccion: Transaction) =
        dao.updateTransaction(transaccion.toTransactionData())

}