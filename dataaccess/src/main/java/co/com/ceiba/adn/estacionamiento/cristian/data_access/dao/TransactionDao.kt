package co.com.ceiba.adn.estacionamiento.cristian.data_access.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.TransactionData

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transactionData: TransactionData)

    @Query("SELECT * FROM TRANSACTION_DATA WHERE placa LIKE :placa and state = 1")
    fun getTransaction(placa: String): TransactionData?

    @Update
    fun updateTransaction(transactionData: TransactionData)

    @Query("SELECT count(*) from TRANSACTION_DATA where type_vehiculo = :type and state = 1")
    fun validateNumberOfVehicles(type: Int): Int

}