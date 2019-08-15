package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import androidx.lifecycle.ViewModel
import co.com.ceiba.adn.estacionamiento.cristian.core.model.TransactionModel
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.CalcPriceRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.applySchedulers
import io.reactivex.Observable
import java.util.*

class MainViewModel constructor(
    private val transactionRepo: TransactionRepository,
    private val priceRepository: CalcPriceRepository
) : ViewModel() {

    fun insertTransaction(placa: String, type: Int, cilindraje: Int? = 0) =
        transactionRepo.checkNumberOfTransactionsByType(type)
            .flatMap {
                if (it)
                    transactionRepo.insertTransaction(
                        TransactionModel(
                            placa = placa, horaIngreso = Date().time, typeVehiculo = type, cilindraje = cilindraje
                        )
                    )
                else Observable.create { emmiter ->
                    emmiter.onNext(Constants.ERROR_CODE_PARKING_FULL)
                    emmiter.onComplete()
                }
            }
            .applySchedulers()

    private fun getTransaction(placa: String) =
        transactionRepo.getTransaction(placa).toObservable()

    fun calcPrice(placa: String) = getTransaction(placa)
        .flatMap {
            priceRepository.getPrice(it.horaIngreso, Date().time, it.typeVehiculo, it.cilindraje!!)
        }
        .applySchedulers()

    fun payment(placa: String, price: Int) = getTransaction(placa)
        .flatMap {
            transactionRepo.updateTransaction(it, price)
        }.applySchedulers()
}