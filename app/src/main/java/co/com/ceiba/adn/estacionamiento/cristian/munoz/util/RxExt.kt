package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}