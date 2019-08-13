package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import androidx.lifecycle.ViewModel
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.CalcPriceRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.applySchedulers
import java.util.*

class MainViewModel constructor(
    private val transactionRepo: TransactionRepository,
    private val priceRepository: CalcPriceRepository
) : ViewModel() {

    fun insertTransaction(placa: String, type: Int, cilindraje: Int? = 0) =
        transactionRepo.insertTransaction(
            TransactionModel(
                placa = placa, horaIngreso = Date().time, typeVehiculo = type, cilindraje = cilindraje
            )
        )
            .applySchedulers()

    private fun getTransaction(placa: String) =
        transactionRepo.getTransaction(placa)
            .applySchedulers()

    fun calcPrice(placa: String) = getTransaction(placa)
        .flatMap {
            priceRepository.calcPrice(it.horaIngreso, Date().time, it.typeVehiculo, it.cilindraje!!)
        }
        .applySchedulers()
}