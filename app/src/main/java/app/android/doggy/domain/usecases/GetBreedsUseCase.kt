package app.android.doggy.domain.usecases

import app.android.doggy.core.common.Resource
import app.android.doggy.data.remote.model.Breed
import app.android.doggy.domain.repository.GetBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(private val getBreedsRepository: GetBreedsRepository) {

    operator fun invoke() : Flow<Resource<List<Breed>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = getBreedsRepository.getList()))
        } catch (e : Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}