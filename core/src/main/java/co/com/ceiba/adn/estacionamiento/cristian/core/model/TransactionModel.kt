package co.com.ceiba.adn.estacionamiento.cristian.core.model

class TransactionModel(
    val id:Long? = null,
    val placa: String,
    val horaIngreso: Long,
    var horaSalida: Long = 0,
    val typeVehiculo: Int,
    var state: Boolean = true,
    val cilindraje:Int? = 0
)