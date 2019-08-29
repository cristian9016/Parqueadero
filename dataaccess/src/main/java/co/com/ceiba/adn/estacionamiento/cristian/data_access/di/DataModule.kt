package co.com.ceiba.adn.estacionamiento.cristian.data_access.di

import co.com.ceiba.adn.estacionamiento.cristian.core.repository.real.TransactionDataManager
import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionDataManagerImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        get<AppDatabase>().transactionDao()
    }

    single<TransactionDataManager> { TransactionDataManagerImpl(get()) }
}