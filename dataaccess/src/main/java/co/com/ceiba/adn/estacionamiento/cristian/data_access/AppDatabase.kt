package co.com.ceiba.adn.estacionamiento.cristian.data_access

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import co.com.ceiba.adn.estacionamiento.cristian.data_access.entity.TransactionData

@Database(entities = arrayOf(TransactionData::class), version = 1,exportSchema = false  )
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {

        fun getInstance(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "ParqueaderoDB")
                .build()

    }
}