package co.com.ceiba.adn.estacionamiento.cristian.data_access.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction: Transaction)

    @Query("select * from transaction")
    fun getAll():List<Transaction>

}