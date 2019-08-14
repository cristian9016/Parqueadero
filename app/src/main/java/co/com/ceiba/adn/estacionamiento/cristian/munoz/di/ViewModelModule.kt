package co.com.ceiba.adn.estacionamiento.cristian.munoz.di

import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}