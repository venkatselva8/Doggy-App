package app.android.doggy.model

data class DogBreedImagesResponse(
    val status: String,
    val message: List<String>,
    val code: Int
)