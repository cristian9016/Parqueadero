package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import io.reactivex.Observable

interface Price {
    fun getPrice(inDate: Long, outDate: Long, typeVehicle: Int, cilindraje: Int): Int
}