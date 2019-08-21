package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.PriceInterface
import io.reactivex.Observable

class CalcPriceRepository constructor(private val price: PriceInterface) {

    fun getPrice(inDate: Long, outDate: Long, typeVehicle: Int, cilindraje: Int): Observable<Int> =
        price.getPrice(inDate, outDate, typeVehicle, cilindraje)

}