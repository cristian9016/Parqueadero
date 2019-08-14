package co.com.ceiba.adn.estacionamiento.cristian.munoz.di

import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.Price
import co.com.ceiba.adn.estacionamiento.cristian.core.negocio.ProcessTransaction
import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import co.com.ceiba.adn.estacionamiento.cristian.data_access.manage_data.TransactionData
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.CalcPriceRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.repository.TransactionRepository
import co.com.ceiba.adn.estacionamiento.cristian.munoz.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { TransactionRepository(get()) }
    single { CalcPriceRepository(get()) }

    single {
        AppDatabase.getInstance(androidApplication())
    }

    single {
        get<AppDatabase>().transactionDao()
    }

    single { TransactionData(get()) }
    single {
        ProcessTransaction(get())
    }
    single { Price() }

    viewModel {
        MainViewModel(get(), get())
    }

}