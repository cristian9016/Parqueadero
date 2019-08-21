package co.com.ceiba.adn.estacionamiento.cristian.munoz.util

import android.os.Build
import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton


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

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun AppCompatActivity.infoAlert(bodyMsg: Int) = alert {
    icon = getDrawable(R.drawable.ic_info)!!
    title = getString(R.string.info_title)
    message = getString(bodyMsg)
    yesButton { title = getString(R.string.btn_accept_text) }
}.show()