package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.*
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.yesButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    lateinit var progress: ProgressDialog
    val dis = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set no emojis filter to editText
        etPlaca.noEmojis()
        etCilindraje.noEmojis()

        //progress dialog config
        configDialog()

        //---------------------radio group listener---------------------
        rgTypeVehicle.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.rbCarro -> {
                    etCilindraje.isEnabled = false
                    etCilindraje.setText("")
                }
                else -> etCilindraje.isEnabled = true
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

//--------------------------------------click en boton de agregar transaction------------------------
        dis add btnAddIn.clicks()
            .flatMap {
                //se valida que los campos no esten vacios
                validateForm(
                    etPlaca.toText(),
                    if (etCilindraje.toText().isNotEmpty()) etCilindraje.toText() else "0"
                )
            }
            .flatMap {
                progress.show()
                //se inicia el proceso de insercion la transaccion
                mainViewModel.insertTransaction(
                    it[0],
                    if (rbCarro.isChecked) Constants.TYPE_CAR else Constants.TYPE_MOTORCYCLE,
                    it[1].toInt()
                )
            }
            .subscribeBy( //resultado del proceso de insercion
                onNext = {
                    progress.hide()
                    when (it) {
                        Constants.INSERT_SUCCESS -> {
                            infoAlert(R.string.inserted_correctly)
                            rbCarro.isChecked = true
                        }
                        Constants.ERROR_CODE_UNAUTHORIZED_PLATE -> infoAlert(R.string.unauthorized_plate)
                        Constants.ERROR_CODE_PARKING_FULL -> infoAlert(R.string.parking_full)
                        Constants.ERROR_CODE_VECHICLE_EXIST -> infoAlert(R.string.vehicle_exist)
                    }
                },
                onError = {
                    it.printStackTrace()
                    infoAlert(R.string.unknown_error)
                    progress.hide()
                }
            )
//----------------------------------------calcular precio--------------------------
        dis add btnCalcPrice.clicks()
            .flatMap {
                validateForm(etPlaca.toText())
            }
            .flatMap {
                progress.show()
                mainViewModel.calcPrice(it[0])
            }
            .subscribeBy(
                onNext = {
                    when (it) {
                        Constants.ERROR_CODE_VEHICLE_DOES_NOT_EXIST -> {
                            infoAlert(R.string.not_found_vehicle)
                            tvValueToPay.text = getString(R.string.value_to_pay_text)
                        }
                        else -> tvValueToPay.text = it.toString()
                    }
                    progress.hide()
                },
                onError = {
                    it.printStackTrace()
                    progress.hide()
                }
            )

//----------------------------------------realizar el pago----------------------------------------
        dis add btnPayment.clicks()
            .subscribe {
                if (tvValueToPay.text == getString(R.string.value_to_pay_text))
                    infoAlert(R.string.calc_value_to_pay_first)
                else pay()
            }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun pay() = validateForm(etPlaca.toText())
        .flatMap {
            progress.show()
            mainViewModel.payment(it[0], tvValueToPay.toInt())
        }
        .subscribeBy(
            onNext = {
                progress.hide()
                infoAlert(R.string.payment_succeed)
            },
            onError = {
                it.printStackTrace()
                infoAlert(R.string.unknown_error)
                tvValueToPay.text = getString(R.string.value_to_pay_text)
                progress.hide()
            }
        )


    //configurar dialog
    private fun configDialog() {
        progress = indeterminateProgressDialog(R.string.please_wait)
        progress.setCancelable(false)
        progress.hide()
    }

}
