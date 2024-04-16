package app.android.doggy.data.repository

import app.android.doggy.data.remote.model.Breed
import app.android.doggy.data.remote.network.ApiService
import app.android.doggy.domain.repository.GetBreedsRepository
import javax.inject.Inject

class GetBreedsRepositoryImpl @Inject constructor(private val apiService : ApiService) :
    GetBreedsRepository {

    override suspend fun getList(): List<Breed> {
        return apiService.getBreedsList().message.keys.map { Breed(it) }
    }
}