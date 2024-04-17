package app.android.doggy.presentation.breed.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import app.android.doggy.R
import app.android.doggy.core.utils.Constants
import app.android.doggy.presentation.breed.components.ErrorView
import app.android.doggy.presentation.breed.components.ItemBreedCard
import app.android.doggy.presentation.breed.components.ProgressBarView
import app.android.doggy.presentation.breed.viewmodels.BreedsViewModel

// BreedsScreen: Displays a list of dog breeds
@Composable
fun BreedsScreen(
    navController: NavHostController,
    breedsViewModel: BreedsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                val breedsStateHolder = breedsViewModel.breedsStateHolder

                if (breedsStateHolder.value.isLoading) {
                    ProgressBarView()
                }

                breedsStateHolder.value.error.takeIf { it.isNotBlank() }?.let {
                    ErrorView(it)
                }

                breedsStateHolder.value.breeds?.let {
                    if (it.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.padding(horizontal = 12.dp).testTag("breedList")) {
                            items(it) { breed ->
                                // Display a card for each dog breed
                                ItemBreedCard(
                                    breed,
                                    onItemClicked = { selectedBreed ->
                                        navController.navigate("${Constants.BREED_IMAGES}/${selectedBreed.name}")
                                    }
                                )
                            }
                        }
                    } else {
                        ErrorView(Constants.ERROR_MESSAGE)
                    }
                }
            }
        })
}

// TopBar: Composable for displaying the top bar
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("topBarBreeds")
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}