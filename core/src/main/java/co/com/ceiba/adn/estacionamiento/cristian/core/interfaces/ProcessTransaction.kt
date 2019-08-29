package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction

interface ProcessTransaction {

    //verificar placa, verificar que no exista e insertar
    fun insertTransaction(transaction: Transaction): Int

    fun getTransaction(placa: String): Any

    fun updateTransaction(transaction: Transaction, price: Int)

    fun validateTransactionforInsertion(transaction: Transaction, day: Int): Int
}