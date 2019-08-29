package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.main

import androidx.lifecycle.ViewModel
import co.com.ceiba.adn.estacionamiento.cristian.core.model.Transaction
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.munoz.extensiones.applySchedulers
import io.reactivex.Observable
import java.util.*

class MainViewModel constructor(
    private val price: Price,
    private val processTransaction: ProcessTransaction
) : ViewModel() {

    //region insert transaction
    fun insertTransaction(placa: String, type: Int, cilindraje: Int? = 0): Observable<Int> =
        Observable.create<Int> {

            val transaction = Transaction(
                placa = placa,
                horaIngreso = Date().time,
                typeVehiculo = type,
                cilindraje = cilindraje
            )
            val actualDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

            val result = processTransaction.validateTransactionforInsertion(transaction, actualDay)

            it.onNext(result)
        }
            .applySchedulers()
    //endregion

    //region calcular precio
    fun calcPrice(placa: String) =
        Observable.fromCallable { processTransaction.getTransaction(placa) }

            .map { response ->

                when (response) {

                    is Transaction -> price.getPrice(
                        response.horaIngreso,
                        Date().time,
                        response.typeVehiculo,
                        response.cilindraje!!
                    )

                    is Int -> response

                    else -> 0
                }
            }
            .applySchedulers()
    //endregion

    //region pago
    fun payment(placa: String, price: Int) =
        Observable.fromCallable { processTransaction.getTransaction(placa) }

            .flatMap {
                Observable.just(processTransaction.updateTransaction(it as Transaction, price))
            }
            .applySchedulers()
    //endregion

}