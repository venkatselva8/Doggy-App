package app.android.doggy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.android.doggy.ui.component.dogBreedImages.DogBreedImagesScreen
import app.android.doggy.ui.component.dogBreedsList.DogBreedsListScreen
import androidx.navigation.compose.composable
import app.android.doggy.util.Constants


// DoggyNavigation: Defines the navigation for the app using Jetpack Navigation
@Composable
fun DoggyNavigation() {
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination = Screen.BreedList.route) {
        composable(Screen.BreedList.route) {
            DogBreedsListScreen(navController = navController, hiltViewModel())
        }
        composable("${Screen.BreedImages.route}${Constants.BREED_NAME_ROUTE}") { backStackEntry ->
            val breedName = backStackEntry.arguments?.getString("name")
            breedName?.let {
                DogBreedImagesScreen(navController = navController, hiltViewModel(), it)
            }
        }
    }
}
