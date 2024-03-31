package app.android.doggy.network

import app.android.doggy.model.DogBreedImagesResponse
import app.android.doggy.model.DogBreedResponse
import retrofit2.http.GET
import retrofit2.http.Path

// ApiService: Interface defining API endpoints
interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreedsList(): DogBreedResponse

    @GET("breed/{breedName}/images/random/10")
    suspend fun getBreedImages(@Path("breedName") breedName: String): DogBreedImagesResponse
}
