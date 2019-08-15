package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.core.util.Constants
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.LifeDisposable
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.toInt
import co.com.ceiba.adn.estacionamiento.cristian.munoz.util.toText
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
    val dis = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    @SuppressLint("CheckResult")
    override fun onResume() {
        super.onResume()

        //--------------------click en boton de agregar transaction------------------------
        dis add btnAddIn.clicks()
            .flatMap {
                //se valida que los campos no esten vacios
                validateForm(
                    R.string.empty_fields,
                    R.string.placa_size,
                    etPlaca.toText(),
                    if (etCilindraje.toText().isNotEmpty()) etCilindraje.toText() else "0"
                )
            }
            .flatMap {
                progress.show()
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
                    when (it) {
                        Constants.INSERT_SUCCESS -> {
                            alert(R.string.info_title, R.string.inserted_correctly)
                            rbCarro.isChecked = true
                        }
                        Constants.ERROR_CODE_UNAUTHORIZED_PLATE -> alert(
                            R.string.info_title,
                            R.string.unauthorized_plate
                        )
                        Constants.ERROR_CODE_PARKING_FULL -> alert(R.string.info_title, R.string.parking_full)
                        Constants.ERROR_CODE_VECHICLE_EXIST -> alert(R.string.info_title, R.string.vehicle_exist)
                    }
                },
                onError = {
                    it.printStackTrace()
                    alert(R.string.info_title, R.string.unknown_error)
                    progress.hide()
                }
            )
        //------------------------calcular precio--------------------------
        dis add btnCalcPrice.clicks()
            .flatMap {
                validateForm(R.string.empty_fields, R.string.placa_size, etPlaca.toText())
            }
            .flatMap {
                progress.show()
                mainViewModel.calcPrice(it[0])
            }
            .subscribeBy(
                onNext = {
                    progress.hide()
                    tvValueToPay.text = it.toString()
                },
                onError = {
                    it.printStackTrace()
                    tvValueToPay.text = getString(R.string.value_to_pay_text)
                    alert(R.string.info_title, R.string.not_found_vehicle)
                    progress.hide()
                }
            )

        //-----------------------realizar el pago----------------------------------------
        dis add btnPayment.clicks()
            .subscribe {
                if (tvValueToPay.text == getString(R.string.value_to_pay_text))
                    alert(R.string.info_title, R.string.calc_value_to_pay_first)
                else pay()
            }
    }

    private fun pay() = validateForm(R.string.empty_fields, R.string.placa_size, etPlaca.toText())
        .flatMap {
            progress.show()
            mainViewModel.payment(it[0], tvValueToPay.toInt())
        }
        .subscribeBy(
            onNext = {
                progress.hide()
                alert(R.string.info_title, R.string.payment_succeed)
            },
            onError = {
                it.printStackTrace()
                alert(R.string.info_title, R.string.unknown_error)
                tvValueToPay.text = getString(R.string.value_to_pay_text)
                progress.hide()
            }
        )


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
