package app.android.doggy.data.repository

import app.android.doggy.data.remote.network.ApiService
import app.android.doggy.domain.repository.GetBreedImagesRepository
import javax.inject.Inject

class GetBreedImagesRepositoryImpl @Inject constructor(private val apiService: ApiService) : GetBreedImagesRepository {

    override suspend fun getList(breedName: String): List<String> {
        return apiService.getBreedImages(breedName).message
    }
}