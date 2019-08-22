package co.com.ceiba.adn.estacionamiento.cristian.core.di

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.PriceInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransactionInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ValidatePlacaInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ValidatePlaca
import org.koin.dsl.module

val coreModule = module {
    single<ProcessTransactionInterface> {
        ProcessTransaction(get())
    }
    single<PriceInterface> { Price() }
    single<ValidatePlacaInterface>{ ValidatePlaca()}
}