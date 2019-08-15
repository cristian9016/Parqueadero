package co.com.ceiba.adn.estacionamiento.cristian.core.util

object Constants {

    //vehicles type
    const val TYPE_CAR = 0
    const val TYPE_MOTORCYCLE = 1

    //total vehicles
    const val MAX_CARS = 20
    const val MAX_MOTORCYCLES = 10
    //prices
    const val CAR_HOUR = 1000
    const val MOTORCYCLE_HOUR = 500
    const val CAR_DAY = 8000
    const val MOTORCYCLE_DAY = 4000
    const val EXTRA_MOTO = 2000

    //message codes
    const val INSERT_SUCCESS = 0
    const val ERROR_CODE_PARKING_FULL = 1
    const val ERROR_CODE_VECHICLE_EXIST = 2
    const val ERROR_CODE_UNAUTHORIZED_PLATE = 3
    const val ERROR_CODE_VEHICLE_DOES_NOT_EXIST = 4

    //days
    const val SUNDAY = 1
    const val MONDAY = 2

}