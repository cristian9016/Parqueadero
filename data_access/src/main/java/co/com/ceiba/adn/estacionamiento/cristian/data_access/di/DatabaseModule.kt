package co.com.ceiba.adn.estacionamiento.cristian.data_access.di

import androidx.room.Room
import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.dao.TransactionDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "parqueadero"
        ).build()
    }

    single {
        get<AppDatabase>().transactionDao()
    }
}