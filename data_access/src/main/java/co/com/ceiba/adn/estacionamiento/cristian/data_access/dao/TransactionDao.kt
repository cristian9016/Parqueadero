package co.com.ceiba.adn.estacionamiento.cristian.data_access.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaction
import io.reactivex.Flowable

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction: Transaction)

    @Query("select * from `transaction` where placa = :placa and state = true")
    fun getTransaction(placa: String): Flowable<Transaction>

    @Update
    fun updateTransaction(transaction: Transaction)

}