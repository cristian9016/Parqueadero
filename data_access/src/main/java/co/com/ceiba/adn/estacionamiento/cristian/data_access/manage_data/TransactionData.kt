package co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data

import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface

class TransactionData(private val dao: TransactionDao) : DataInterface {

    override fun getData(placa: String): Transaccion =
        dao.getTransaction(placa)

    override fun insertData(transaccion: Transaccion) =
        dao.insertTransaction(transaccion)

    override fun getNumberOfTransactionsByType(type: Int): Int =
        dao.validateNumberOfVehicles(type)

    override fun updateData(transaccion: Transaccion) =
        dao.updateTransaction(transaccion)

}