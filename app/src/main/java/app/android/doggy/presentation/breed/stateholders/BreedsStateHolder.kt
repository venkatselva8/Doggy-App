package app.android.doggy.presentation.breed.stateholders

import app.android.doggy.data.remote.model.Breed

data class BreedsStateHolder(
    var breeds: List<Breed>? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)