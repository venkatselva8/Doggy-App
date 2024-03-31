package app.android.doggy.usecase

import app.android.doggy.data.model.DogBreed
import app.android.doggy.network.ApiService
import app.android.doggy.util.Constants
import javax.inject.Inject

class DogBreedsUseCase @Inject constructor(private val apiService: ApiService) {

    suspend fun getBreedsList(): List<DogBreed>? {
        return try {
            val response = apiService.getBreedsList()
            if (response.status == Constants.STATUS_SUCCESS) {
                val breedMap = response.message
                breedMap.keys.map { DogBreed(it) }
            } else {
                listOf()
            }
        } catch (e: Exception) {
           listOf()
        }
    }

    suspend fun getBreedImages(breedName: String): List<String>? {
        return try {
            val response = apiService.getBreedImages(breedName)
            if (response.status == Constants.STATUS_SUCCESS) {
                response.message
            } else {
                listOf()
            }
        } catch (e: Exception) {
            listOf()
        }
    }
}
