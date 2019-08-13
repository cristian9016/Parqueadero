package co.com.ceiba.adn.estacionamiento.cristian.core.validadores

import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import kotlin.math.floor
import kotlin.math.roundToInt

object Validadores {

    fun validarPlaca(placa: String, day: Int): Boolean {
        var authorized = true
        if (placa.toUpperCase().startsWith("A") && (day != 0 && day != 1))
            authorized = false
        return authorized
    }

    fun calcHours(millis: Long) =
        (millis.toDouble() / 3600000).roundToInt()

    fun calcPrice(hours: Int, typeVehicle: Int, cilindraje: Int): Int =
        when (hours) {
            in 0 until 9 -> {
                when {
                    typeVehicle == Constants.TYPE_CAR -> hours * Constants.CAR_HOUR
                    cilindraje > 500 -> (hours * Constants.MOTORCYCLE_HOUR) + Constants.EXTRA_MOTO
                    else -> hours * Constants.MOTORCYCLE_HOUR
                }
            }
            in 9..24 -> {
                when {
                    typeVehicle == Constants.TYPE_CAR -> Constants.CAR_DAY
                    cilindraje > 500 -> Constants.MOTORCYCLE_DAY + Constants.EXTRA_MOTO
                    else -> Constants.MOTORCYCLE_DAY
                }
            }
            else -> {
                val days = floor((hours / 24).toDouble()).toInt()
                val extraHours = hours - days * 24
                when {
                    typeVehicle == Constants.TYPE_CAR -> (days * Constants.CAR_DAY) + (extraHours * Constants.CAR_HOUR)
                    cilindraje > 500 -> (days * Constants.MOTORCYCLE_DAY) + (extraHours * Constants.MOTORCYCLE_HOUR) + Constants.EXTRA_MOTO
                    else -> (days * Constants.MOTORCYCLE_DAY) + (extraHours * Constants.MOTORCYCLE_HOUR)
                }
            }
        }
}