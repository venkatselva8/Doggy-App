package app.android.doggy.presentation.dogBreedsList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.android.doggy.util.Constants
import app.android.doggy.util.showToast

// DogBreedsListScreen: Displays a list of dog breeds
@Composable
fun DogBreedsListScreen(
    navController: NavHostController,
    dogBreedsListViewModel: DogBreedsListViewModel = hiltViewModel()
) {
    val dogBreedsState = dogBreedsListViewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        dogBreedsListViewModel.showProgress()
        dogBreedsListViewModel.getDogBreedsList()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onTertiary)
    ) {
        item {
            TopBar()
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Display a progress indicator while loading
            if (dogBreedsState.isLoading) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillParentMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        dogBreedsState.dogBreeds?.let { list ->
            if (list.isEmpty()) {
                context.showToast(Constants.ERROR_MESSAGE)
            }
            items(list) { breed ->
                // Display a card for each dog breed
                ItemDogCard(
                    breed,
                    onItemClicked = { selectedBreed ->
                        navController.navigate("${Constants.BREED_IMAGES}/${selectedBreed.name}")
                    }
                )
            }
        }
    }
}