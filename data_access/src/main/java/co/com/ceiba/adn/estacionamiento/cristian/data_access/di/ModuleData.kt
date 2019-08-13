package co.com.ceiba.adn.estacionamiento.cristian.data_access.di

import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import org.koin.dsl.module

val dataModule = module {
    single { TransactionData(get()) }
}