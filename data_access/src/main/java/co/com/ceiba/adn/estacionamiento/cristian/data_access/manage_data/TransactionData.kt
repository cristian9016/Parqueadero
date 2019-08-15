package co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data

import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface

class TransactionData(private val dao: TransactionDao) {

    fun getData(placa: String): Transaccion? =
        dao.getTransaction(placa)

    fun insertData(transaccion: Transaccion) =
        dao.insertTransaction(transaccion)

    fun getNumberOfTransactionsByType(type: Int): Int =
        dao.validateNumberOfVehicles(type)

    fun updateData(transaccion: Transaccion) =
        dao.updateTransaction(transaccion)

}