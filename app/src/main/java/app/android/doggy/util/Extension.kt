package app.android.doggy.util

import android.content.Context
import android.widget.Toast
import java.util.Locale

/**
 * Extension function to capitalize the first letter of a String.
 * @return A String with the first letter capitalized.
 */
fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }
}

/**
 * Extension function to show a toast message in a Context.
 * @param message The message to be displayed in the toast.
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
