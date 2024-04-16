package app.android.doggy.core.utils

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
