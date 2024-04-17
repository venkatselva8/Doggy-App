package app.android.doggy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.android.doggy.presentation.breed.screens.BreedImagesScreen
import app.android.doggy.presentation.breed.screens.BreedsScreen
import androidx.navigation.compose.composable
import app.android.doggy.core.utils.Constants

// DoggyNavigation: Defines the navigation for the app using Jetpack Navigation
@Composable
fun DoggyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.BreedList.route) {
        composable(Screen.BreedList.route) {
            BreedsScreen(navController = navController, hiltViewModel())
        }
        composable("${Screen.BreedImages.route}${Constants.BREED_NAME_ROUTE}") { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString(Constants.KEY_SELECTED_BREED_NAME)
            breedName?.let {
                BreedImagesScreen(navController = navController, hiltViewModel(), it)
            }
        }
    }
}
