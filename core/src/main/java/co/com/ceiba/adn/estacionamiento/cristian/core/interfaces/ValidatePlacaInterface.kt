package co.com.ceiba.adn.estacionamiento.cristian.core.interfaces

import io.reactivex.Observable

interface ValidatePlacaInterface {
    fun validatePlaca(placa:String,day:Int): Observable<Int>
}