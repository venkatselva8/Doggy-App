package app.android.doggy.ui.component.dogBreedImages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.doggy.usecase.DogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogBreedImagesViewModel @Inject constructor(
    private val dogBreedsUseCase: DogBreedsUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(DogBreedImagesUiState())
        private set

    fun getDogBreedImages(name: String) {
        viewModelScope.launch {
            uiState = try {
                val breeds = dogBreedsUseCase.getBreedImages(name)
                DogBreedImagesUiState(dogBreedsImages = breeds, isLoading = false)
            } catch (e: Exception) {
                DogBreedImagesUiState(isLoading = false, dogBreedsImages = emptyList())
            }
        }
    }

}