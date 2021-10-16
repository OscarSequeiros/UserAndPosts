package co.com.ceiba.mobile.pruebadeingreso.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showToast(message: String, duration: Int) {
    Toast.makeText(this, message, duration).show()
}