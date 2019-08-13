package co.com.ceiba.adn.estacionamiento.cristian.core.util

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction

object EntityToModel {

    fun transactionModel(transaction: Transaction): TransactionModel =
        TransactionModel(
            transaction.id,
            transaction.placa,
            transaction.horaIngreso,
            transaction.horaSalida,
            transaction.typeVehiculo,
            transaction.state,
            transaction.cilindraje
        )

    fun transactionEntity(transaction: TransactionModel): Transaction =
        Transaction(
            transaction.id,
            transaction.placa,
            transaction.horaIngreso,
            transaction.horaSalida,
            transaction.typeVehiculo,
            transaction.state,
            transaction.cilindraje!!
        )


}