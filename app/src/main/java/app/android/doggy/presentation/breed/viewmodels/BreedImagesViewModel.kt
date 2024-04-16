package app.android.doggy.presentation.breed.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.doggy.core.common.Resource
import app.android.doggy.core.utils.Constants
import app.android.doggy.domain.usecases.GetBreedImagesUseCase
import app.android.doggy.presentation.breed.stateholders.BreedImagesStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State

// DogBreedImagesViewModel: ViewModel for managing dog breed images
@HiltViewModel
class BreedImagesViewModel @Inject constructor(
    private val breedImagesUseCase: GetBreedImagesUseCase, savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _breedImagesStateHolderHolder = mutableStateOf(BreedImagesStateHolder())
    val breedImagesStateHolderHolder: State<BreedImagesStateHolder> = _breedImagesStateHolderHolder

    init {
        viewModelScope.launch {
            savedStateHandle.getStateFlow(Constants.KEY_SELECTED_BREED_NAME, "").collectLatest {
                getDogBreedImages(it)
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getDogBreedImages(name: String) {
        breedImagesUseCase(name).onEach {
            when (it) {
                is Resource.Loading -> {
                    _breedImagesStateHolderHolder.value = BreedImagesStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    _breedImagesStateHolderHolder.value = BreedImagesStateHolder(breedImages = it.data)
                }

                is Resource.Error -> {
                    _breedImagesStateHolderHolder.value = BreedImagesStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

}