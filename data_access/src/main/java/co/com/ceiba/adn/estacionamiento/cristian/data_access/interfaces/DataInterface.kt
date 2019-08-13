package co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction
import io.reactivex.Flowable

interface DataInterface {
    fun insertData(transaction: Transaction)
    fun getData(placa:String): Flowable<Transaction>
    fun updateData(transaction: Transaction)
}