package co.com.ceiba.adn.estacionamiento.cristian.core.dominio


import co.com.ceiba.adn.estacionamiento.cristian.core.Calculos.calcularHoras
import co.com.ceiba.adn.estacionamiento.cristian.core.Calculos.calcularPrecio
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.Price

class PriceImpl : Price {

    override fun getPrice(
        inDate: Long,
        outDate: Long,
        typeVehicle: Int,
        cilindraje: Int
    ): Int {
        val hours = calcularHoras(outDate - inDate)
        return calcularPrecio(hours, typeVehicle, cilindraje)
    }


}