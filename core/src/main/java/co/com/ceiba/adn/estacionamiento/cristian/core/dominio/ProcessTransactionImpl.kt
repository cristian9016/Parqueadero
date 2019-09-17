package co.com.ceiba.adn.estacionamiento.cristian.core.dominio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.repository.TransactionDataManager
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.core.Constants
import java.util.*

class ProcessTransactionImpl constructor(private val transactionData: TransactionDataManager) :
    ProcessTransaction {

    //total vehicles
    val MAX_CARS = 20
    val MAX_MOTORCYCLES = 10

    //days
    val DOMINGO = 1
    val LUNES = 2

    //region obtener transaccion
    override fun getTransaction(placa: String): Any {
        return transactionData.getData(placa) ?: Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST
    }
    //endregion

    //region insertar transaction
    override fun insertTransaction(transaction: Transaction): Int {
        transactionData.insertData(transaction)
        return Constants.INSERT_SUCCESS
    }
    //endregion

    //region actualizar transaccion
    override fun updateTransaction(
        transaction: Transaction,
        price: Int
    ) {
        transaction.horaSalida = Date().time
        transaction.state = false
        transaction.price = price
        transactionData.updateData(transaction)
    }
    //endregion

    //region validar transaccion para insertarla
    override fun validateTransactionforInsertion(
        transaction: Transaction,
        day: Int
    ): Int {
        return if (checkNumberOfTransactionsByType(transaction.typeVehiculo))
            if (transaction.placa.toUpperCase().startsWith("A") && (day != DOMINGO && day != LUNES))
                Constants.ERROR_CODE_UNAUTHORIZED_PLATE
            else {
                val transaccion = getTransaction(transaction.placa)
                if (transaccion == Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST) {
                    insertTransaction(transaction)
                    Constants.INSERT_SUCCESS
                } else Constants.ERROR_CODE_VECHICLE_EXIST
            }
        else Constants.ERROR_CODE_PARKING_FULL
    }
    //endregion

    //region verificar espacios disponibles
    private fun checkNumberOfTransactionsByType(type: Int): Boolean {
        val count = transactionData.getNumberOfTransactionsByType(type)
        return (type == Constants.TYPE_CAR && count < MAX_CARS) ||
                (type == Constants.TYPE_MOTORCYCLE && count < MAX_MOTORCYCLES)
    }
    //endregion

}