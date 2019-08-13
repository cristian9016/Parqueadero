package co.com.ceiba.adn.estacionamiento.cristian.munoz.repository

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.Price
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.applySchedulers

class CalcPriceRepository constructor(private val price: Price) {

    fun calcPrice(inDate: Long, outDate: Long, typeVehicle: Int, cilindraje: Int) =
        price.price(inDate, outDate, typeVehicle, cilindraje)
            .applySchedulers()

}