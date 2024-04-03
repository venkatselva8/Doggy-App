package app.android.doggy.presentation.dogBreedImages

data class DogBreedImagesUiState (
    val dogBreedsImages: List<String>? = null,
    val isLoading: Boolean = false,
)