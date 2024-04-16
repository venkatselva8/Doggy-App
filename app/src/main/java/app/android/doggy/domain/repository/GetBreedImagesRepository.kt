package app.android.doggy.domain.repository

interface GetBreedImagesRepository {
    suspend fun getList(breedName:String): List<String>
}