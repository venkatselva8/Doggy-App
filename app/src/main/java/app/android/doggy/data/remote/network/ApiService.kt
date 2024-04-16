package app.android.doggy.data.remote.network

import app.android.doggy.data.remote.model.BreedImagesResponse
import app.android.doggy.data.remote.model.BreedsResponse
import retrofit2.http.GET
import retrofit2.http.Path

// ApiService: Interface defining API endpoints
interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreedsList(): BreedsResponse

    @GET("breed/{breedName}/images/random/10")
    suspend fun getBreedImages(@Path("breedName") breedName: String): BreedImagesResponse
}
