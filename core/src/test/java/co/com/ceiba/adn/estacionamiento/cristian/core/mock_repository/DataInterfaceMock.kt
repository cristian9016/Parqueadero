package co.com.ceiba.adn.estacionamiento.cristian.core.mock_repository

import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface

class DataInterfaceMock:DataInterface {
    override fun insertData(transaccion: Transaccion) = Unit

    override fun getData(placa:String): Transaccion? = null

    override fun updateData(transaccion: Transaccion) = Unit
    override fun getNumberOfTransactionsByType(type:Int):Int = 10
}