package co.com.ceiba.adn.estacionamiento.cristian.core.di

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import org.koin.dsl.module

val coreModule = module {
    single {
        ProcessTransaction(get())
    }
}