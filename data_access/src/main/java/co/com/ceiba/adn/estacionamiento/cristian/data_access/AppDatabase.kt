package co.com.ceiba.adn.estacionamiento.cristian.data_access

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.Transaccion

@Database(entities = arrayOf(Transaccion::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        fun getInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "ParqueaderoDB")
                .build()
    }
}