package app.android.doggy.ui.component.dogBreedsList

import app.android.doggy.model.DogBreed

data class DogBreedsListUiState (
    var dogBreeds: List<DogBreed>? = null,
    val isLoading: Boolean = true
)