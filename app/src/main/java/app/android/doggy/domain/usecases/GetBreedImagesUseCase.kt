package app.android.doggy.domain.usecases

import app.android.doggy.core.common.Resource
import app.android.doggy.domain.repository.GetBreedImagesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBreedImagesUseCase @Inject constructor(private val getBreedImagesRepository: GetBreedImagesRepository) {

    operator fun invoke(breedName: String): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getBreedImagesRepository.getList(breedName)))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}