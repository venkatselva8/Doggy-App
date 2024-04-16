package app.android.doggy.data.remote.model

data class BreedImagesResponse(
    val status: String,
    val message: List<String>,
    val code: Int
)