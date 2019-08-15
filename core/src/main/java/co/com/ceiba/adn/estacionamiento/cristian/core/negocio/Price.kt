package co.com.ceiba.adn.estacionamiento.cristian.core.negocio


import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores.calcHours
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores.calcPrice
import io.reactivex.Observable

class Price {

    fun price(inDate: Long, outDate: Long, typeVehicle: Int, cilindraje: Int): Observable<Int> =
        Observable.create {
            val hours = calcHours(outDate - inDate)
            var total = calcPrice(hours, typeVehicle, cilindraje)
            it.onNext(total)
            it.onComplete()
        }

}