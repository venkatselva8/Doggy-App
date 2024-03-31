package app.android.doggy.model

data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>,
    val code: Int,
)