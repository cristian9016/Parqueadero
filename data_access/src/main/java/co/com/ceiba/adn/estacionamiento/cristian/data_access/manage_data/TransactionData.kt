package co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data

import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface
import io.reactivex.Flowable

class TransactionData(private val dao: TransactionDao) : DataInterface {

    override fun getData(placa: String): Flowable<Transaction> =
        dao.getTransaction(placa)

    override fun insertData(transaction: Transaction) =
        dao.insertTransaction(transaction)

    override fun updateData(transaction: Transaction) =
        dao.updateTransaction(transaction)

}