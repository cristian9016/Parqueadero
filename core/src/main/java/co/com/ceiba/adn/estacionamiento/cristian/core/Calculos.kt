package co.com.ceiba.adn.estacionamiento.cristian.core

import kotlin.math.ceil
import kotlin.math.floor

object Calculos {

    //prices
    private const val CAR_HOUR = 1000
    private const val MOTORCYCLE_HOUR = 500
    private const val CAR_DAY = 8000
    private const val MOTORCYCLE_DAY = 4000
    private const val EXTRA_MOTO = 2000

    fun calcularHoras(millis: Long) =
        ceil(millis.toDouble() / 3600000).toInt()

    fun calcularPrecio(hours: Int, typeVehicle: Int, cilindraje: Int): Int =
        when (hours) {
            in 0 until 9 -> {
                when {
                    typeVehicle == Constants.TYPE_CAR -> hours * CAR_HOUR
                    cilindraje > 500 -> (hours * MOTORCYCLE_HOUR) + EXTRA_MOTO
                    else -> hours * MOTORCYCLE_HOUR
                }
            }
            in 9..24 -> {
                when {
                    typeVehicle == Constants.TYPE_CAR -> CAR_DAY
                    cilindraje > 500 -> MOTORCYCLE_DAY + EXTRA_MOTO
                    else -> MOTORCYCLE_DAY
                }
            }
            else -> {
                val days = floor((hours / 24).toDouble()).toInt()
                val extraHours = hours - days * 24
                when {
                    typeVehicle == Constants.TYPE_CAR -> (days * CAR_DAY) + (extraHours * CAR_HOUR)
                    cilindraje > 500 -> (days * MOTORCYCLE_DAY) + (extraHours * MOTORCYCLE_HOUR) + EXTRA_MOTO
                    else -> (days * MOTORCYCLE_DAY) + (extraHours * MOTORCYCLE_HOUR)
                }
            }
        }
}