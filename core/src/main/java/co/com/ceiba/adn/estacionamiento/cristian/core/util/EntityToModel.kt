package co.com.ceiba.adn.estacionamiento.cristian.core.util

import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion

object EntityToModel {

    fun transactionModel(transaccion: Transaccion): TransactionModel =
        TransactionModel(
            transaccion.id,
            transaccion.placa,
            transaccion.horaIngreso,
            transaccion.horaSalida,
            transaccion.typeVehiculo,
            transaccion.state,
            transaccion.cilindraje
        )

    fun transactionEntity(transaction: TransactionModel): Transaccion =
        Transaccion(
            transaction.id,
            transaction.placa,
            transaction.horaIngreso,
            transaction.horaSalida,
            transaction.typeVehiculo,
            transaction.state,
            transaction.cilindraje!!
        )


}