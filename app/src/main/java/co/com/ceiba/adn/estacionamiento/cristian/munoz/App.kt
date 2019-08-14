package co.com.ceiba.adn.estacionamiento.cristian.munoz

import android.app.Application
import co.com.ceiba.adn.estacionamiento.cristian.munoz.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //android context
            androidContext(this@App)
            //modules
            modules(appModule)

        }
    }
}