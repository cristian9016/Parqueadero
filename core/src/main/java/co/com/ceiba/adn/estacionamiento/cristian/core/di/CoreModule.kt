package co.com.ceiba.adn.estacionamiento.cristian.core.di

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.PriceImpl
import co.com.ceiba.adn.estacionamiento.cristian.core.dominio.ProcessTransactionImpl
import org.koin.dsl.module

val coreModule = module {

    single<ProcessTransaction> { ProcessTransactionImpl(get()) }

    single<Price> { PriceImpl() }

}