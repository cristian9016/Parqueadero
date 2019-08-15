package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import android.widget.EditText
import android.widget.TextView

fun EditText.toText() = this.text.toString()
fun TextView.toInt() = this.text.toString().toInt()