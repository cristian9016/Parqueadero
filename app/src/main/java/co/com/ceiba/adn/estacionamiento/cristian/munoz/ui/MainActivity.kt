package co.com.ceiba.adn.estacionamiento.cristian.munoz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}
