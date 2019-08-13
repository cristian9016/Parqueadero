package co.com.ceiba.adn.estacionamiento.cristian.munoz.di

import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TransactionRepository(get()) }
    viewModel { MainViewModel(get()) }

}