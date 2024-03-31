package app.android.doggy.ui.component.dogBreedsList

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.android.doggy.util.Constants

@Composable
fun DogBreedsListScreen(
    navController: NavHostController,
    dogBreedsListViewModel: DogBreedsListViewModel = hiltViewModel()
) {
    val dogBreedsState = dogBreedsListViewModel.uiState

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
            items(list) { breed ->
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