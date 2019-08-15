package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView


fun EditText.toText() = this.text.toString()
fun TextView.toInt() = this.text.toString().toInt()

val filter = InputFilter { source, start, end, dest, dstart, dend ->
    for (i in start until end) {
        if (!Character.isLetterOrDigit(source[i]) && !Character.isWhitespace(source[i])) {
            return@InputFilter ""
        }
    }
    null
}

fun EditText.noEmojis() {
    this.filters = arrayOf(filter)
}