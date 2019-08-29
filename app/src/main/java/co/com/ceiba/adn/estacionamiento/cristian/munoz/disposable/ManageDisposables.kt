package co.com.ceiba.adn.estacionamiento.cristian.munoz.disposable

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ManageDisposables(owner: LifecycleOwner) : LifecycleObserver {

    init {
        owner.lifecycle.addObserver(this)
    }

    val dis: CompositeDisposable = CompositeDisposable()

    infix fun add(disposable: Disposable) {
        dis.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun clearDisposable() {
        dis.clear()
    }
}