package co.com.ceiba.adn.estacionamiento.cristian.data_access.di

import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.interfaces.DataInterface
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import org.koin.dsl.module

val dataModule = module {
    single {
        get<AppDatabase>().transactionDao()
    }

    single<DataInterface> { TransactionData(get()) }
}