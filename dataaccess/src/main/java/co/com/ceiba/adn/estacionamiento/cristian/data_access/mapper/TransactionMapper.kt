package co.com.ceiba.adn.estacionamiento.cristian.data_access.mapper

import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.TransactionData


fun TransactionData.toTransaction() =
    Transaction(
        this.id,
        this.placa,
        this.horaIngreso,
        this.horaSalida,
        this.typeVehiculo,
        this.state,
        this.cilindraje
    )


fun Transaction.toTransactionData() =
    TransactionData(
        this.id,
        this.placa,
        this.horaIngreso,
        this.horaSalida,
        this.typeVehiculo,
        this.state,
        this.cilindraje!!
    )