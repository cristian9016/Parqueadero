package co.com.ceiba.adn.estacionamiento.cristian.munoz.extensiones

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.adn.estacionamiento.cristian.munoz.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

//region extensiones android

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

fun Activity.closeKeyboard(view:View){
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(view.windowToken,0)
}

//endregion