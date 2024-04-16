package app.android.doggy.presentation.breed.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.doggy.core.common.Resource
import app.android.doggy.domain.usecases.GetBreedsUseCase
import app.android.doggy.presentation.breed.stateholders.BreedsStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

// DogBreedsListViewModel: ViewModel for managing dog breeds list
@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val breedsUseCase: GetBreedsUseCase
) : ViewModel() {

    private val _breedsStateHolderHolder = mutableStateOf(BreedsStateHolder())
    val breedsStateHolder: State<BreedsStateHolder> = _breedsStateHolderHolder

    init {
        getDogBreedsList()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getDogBreedsList() {
        breedsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _breedsStateHolderHolder.value = BreedsStateHolder(isLoading = true)
                }

                is Resource.Success -> {
                    _breedsStateHolderHolder.value = BreedsStateHolder(breeds = it.data)
                }

                is Resource.Error -> {
                    _breedsStateHolderHolder.value =
                        BreedsStateHolder(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}