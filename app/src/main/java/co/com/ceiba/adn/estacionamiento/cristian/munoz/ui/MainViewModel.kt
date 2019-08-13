package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import androidx.lifecycle.ViewModel
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.applySchedulers
import java.util.*

class MainViewModel constructor(private val repository: TransactionRepository) : ViewModel() {

    fun insertTransaction(placa: String, type: Int, cilindraje: Int? = 0) =
        repository.insertTransaction(
            TransactionModel(
                placa = placa, horaIngreso = Date().time, typeVehiculo = type,cilindraje = cilindraje
            )
        ).applySchedulers()

}