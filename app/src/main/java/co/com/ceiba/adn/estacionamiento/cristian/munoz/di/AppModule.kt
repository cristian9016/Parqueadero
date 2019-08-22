package co.com.ceiba.adn.estacionamiento.cristian.munoz.di

import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.CalcPriceRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single {
        AppDatabase.getInstance(androidApplication())
    }
    single { TransactionRepository(get(),get()) }
    single { CalcPriceRepository(get()) }

}