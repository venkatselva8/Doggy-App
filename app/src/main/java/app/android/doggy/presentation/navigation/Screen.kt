package app.android.doggy.presentation.navigation

import app.android.doggy.core.utils.Constants

sealed class Screen(val route: String) {
    data object BreedList : Screen(Constants.BREED_LIST)
    data object BreedImages : Screen(Constants.BREED_IMAGES)
}
