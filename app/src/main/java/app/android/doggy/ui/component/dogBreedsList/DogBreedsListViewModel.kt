package app.android.doggy.ui.component.dogBreedsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.doggy.usecase.DogBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// DogBreedsListViewModel: ViewModel for managing dog breeds list
@HiltViewModel
class DogBreedsListViewModel @Inject constructor(
    private val dogBreedsUseCase: DogBreedsUseCase
) : ViewModel() {
    var uiState by mutableStateOf(DogBreedsListUiState())
        private set

     fun getDogBreedsList() {
        viewModelScope.launch {
            uiState = try {
                val breeds = dogBreedsUseCase.getBreedsList()
                DogBreedsListUiState(dogBreeds = breeds, isLoading = false)
            } catch (e: Exception) {
                DogBreedsListUiState(dogBreeds = emptyList(), isLoading = false)
            }
        }
    }

    fun showProgress(){
        uiState = DogBreedsListUiState(isLoading = true)
    }
}