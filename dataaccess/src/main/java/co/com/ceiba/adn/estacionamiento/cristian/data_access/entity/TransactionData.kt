package co.com.ceiba.adn.estacionamiento.cristian.data_access.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRANSACTION_DATA")
data class TransactionData(

    @PrimaryKey val id: Long?,
    val placa: String,

    @ColumnInfo(name = "hora_ingreso")
    val horaIngreso: Long,

    @ColumnInfo(name = "hora_salida")
    var horaSalida: Long,

    @ColumnInfo(name = "type_vehiculo")
    val typeVehiculo: Int,

    var state: Boolean = true,

    val cilindraje: Int,

    var price: Int? = 0
)