package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun AppCompatActivity.validateForm(
    messageEmptyfields: Int,
    messagePlacaMin:Int,
    messagePlacaMax:Int,
    vararg fields: String
): Observable<List<String>> = Observable.create<List<String>> {
    when {
        fields.contains("") -> Toast.makeText(this, messageEmptyfields, Toast.LENGTH_SHORT).show()
        fields[0].length < 5 -> Toast.makeText(this, messagePlacaMin, Toast.LENGTH_SHORT).show()
        fields[0].length > 6 -> Toast.makeText(this, messagePlacaMax, Toast.LENGTH_SHORT).show()
        else -> it.onNext(fields.toList())
    }
    it.onComplete()
}