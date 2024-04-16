package app.android.doggy.data.remote.model

data class BreedsResponse(
    val status: String,
    val message: Map<String, List<String>>,
    val code: Int,
)