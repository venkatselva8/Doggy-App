package app.android.doggy.ui.navigation

import app.android.doggy.util.Constants

sealed class Screen(val route: String) {
    data object BreedList : Screen(Constants.BREED_LIST)
    data object BreedImages : Screen(Constants.BREED_IMAGES)
}
