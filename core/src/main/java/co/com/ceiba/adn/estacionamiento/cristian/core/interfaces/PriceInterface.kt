package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import io.reactivex.Observable

interface PriceInterface {
    fun getPrice(inDate: Long, outDate: Long, typeVehicle: Int, cilindraje: Int): Observable<Int>
}