package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.text
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.validateForm
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.yesButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    lateinit var progress: ProgressDialog

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //progress dialog config
        configDialog()

        //radio group listener
        rgTypeVehicle.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.rbCarro -> {
                    etCilindraje.isEnabled = false
                    etCilindraje.setText("")
                }
                else -> etCilindraje.isEnabled = true
            }
        }

        //click en boton de agregar transaction
        btnAddIn.clicks()
            .flatMap {
                //se valida que los campos no esten vacios
                progress.show()
                if (rbMoto.isChecked) validateForm(
                    R.string.empty_fields,
                    R.string.placa_size,
                    etPlaca.text(),
                    etCilindraje.text()
                )
                else validateForm(
                    R.string.empty_fields,
                    R.string.placa_size,
                    etPlaca.text(),
                    "0"
                )
            }
            .flatMap {
                //se inserta la transaccion
                mainViewModel.insertTransaction(
                    it[0],
                    if (rbCarro.isChecked) Constants.TYPE_CAR else Constants.TYPE_MOTORCYCLE,
                    it[1].toInt()
                )
            }
            .subscribeBy(
                onNext = {
                    progress.hide()
                    when(it){
                        Constants.INSERT_SUCCESS -> {
                            alert(R.string.info_title, R.string.inserted_correctly)
                            rbCarro.isChecked = true
                        }
                        Constants.ERROR_CODE_UNAUTHORIZED_PLATE -> alert(R.string.info_title, R.string.unauthorized_plate)
                        Constants.ERROR_CODE_PARKING_FULL -> alert(R.string.info_title, R.string.parking_full)
                        Constants.ERROR_CODE_VECHICLE_EXIST -> alert(R.string.info_title, R.string.vehicle_exist)
                    }
                },
                onComplete = {
                    progress.hide()
                },
                onError = {
                    it.printStackTrace()
                    alert(R.string.info_title, R.string.unknown_error)
                    progress.hide()
                }
            )
        //calcular precio
        btnCalcPrice.clicks()
            .flatMap {
                progress.show()
                validateForm(R.string.empty_fields, R.string.placa_size, etPlaca.text())
            }
            .flatMap { mainViewModel.calcPrice(it[0]) }
            .subscribeBy(
                onNext = {
                    progress.hide()
                    tvValueToPay.text = it.toString()
                },
                onError = {
                    it.printStackTrace()
                    alert(R.string.info_title, R.string.not_found_vehicle)
                    progress.hide()
                },
                onComplete = {
                    progress.hide()
                }
            )
    }


    //dialog
    private fun configDialog() {
        progress = indeterminateProgressDialog(R.string.please_wait)
        progress.setCancelable(false)
        progress.hide()
    }

    //alert
    private fun alert(titleMsg: Int, bodyMsg: Int) = alert {
        title = getString(titleMsg)
        message = getString(bodyMsg)
        yesButton { title = getString(R.string.btn_accept_text) }
    }.show()
}
