package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> = compose {
    it.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun AppCompatActivity.validateForm(
    vararg fields: String
): Observable<List<String>> = Observable.create<List<String>> {
    when {
        fields.contains("") -> Toast.makeText(this, R.string.empty_fields, Toast.LENGTH_SHORT).show()
        fields[0].length < 5 -> Toast.makeText(this,R.string.placa_min_size, Toast.LENGTH_SHORT).show()
        fields[0].length > 6 -> Toast.makeText(this, R.string.placa_max_size, Toast.LENGTH_SHORT).show()
        else -> it.onNext(fields.toList())
    }
    it.onComplete()
}