package co.com.ceiba.adn.estacionamiento.cristian.core.negocio

import co.com.ceiba.adn.estacionamiento.cristian.core.interfaces.ValidatePlacaInterface
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.core.validadores.Validadores
import io.reactivex.Observable

class ValidatePlaca:ValidatePlacaInterface {

    override fun validatePlaca(placa: String,day:Int): Observable<Int> =
        Observable.create {
            val authorized = Validadores.validarPlaca(placa, day)
            if (authorized)
                it.onNext(Constants.ERROR_CODE_AUTHORIZED_PLATE)
            else it.onNext(Constants.ERROR_CODE_UNAUTHORIZED_PLATE)
        }

}