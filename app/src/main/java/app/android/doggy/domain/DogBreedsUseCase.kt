package app.android.doggy.domain

import app.android.doggy.data.model.DogBreed
import app.android.doggy.data.remote.ApiService
import app.android.doggy.util.Constants
import javax.inject.Inject

// DogBreedsUseCase: Use case for fetching dog breeds and images
class DogBreedsUseCase @Inject constructor(private val apiService: ApiService) {

    suspend fun getBreedsList(): List<DogBreed>? {
        return try {
            val response = apiService.getBreedsList()
            if (response.status == Constants.STATUS_SUCCESS) {
                val breedMap = response.message
                breedMap.keys.map { DogBreed(it) }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getBreedImages(breedName: String): List<String>? {
        return try {
            val response = apiService.getBreedImages(breedName)
            if (response.status == Constants.STATUS_SUCCESS) {
                response.message
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
