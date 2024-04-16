package app.android.doggy.presentation.breed.stateholders

data class BreedImagesStateHolder(
    val breedImages: List<String>? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)