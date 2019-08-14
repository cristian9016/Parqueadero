package co.com.ceiba.adn.estacionamiento.cristian.data_access.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaccion: Transaccion)

    @Query("SELECT * FROM transaccion WHERE placa LIKE :placa and state = 1")
    fun getTransaction(placa: String): Transaccion

    @Update
    fun updateTransaction(transaccion: Transaccion)

    @Query("SELECT count(*) from transaccion where type_vehiculo = :type and state = 1")
    fun validateNumberOfVehicles(type: Int): Int

}