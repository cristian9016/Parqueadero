package co.com.ceiba.adn.estacionamiento.cristian.munoz.di

import co.com.ceiba.adn.estacionamiento.cristian.data_access.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {

    single {
        AppDatabase.getInstance(androidApplication())
    }

}