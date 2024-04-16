package app.android.doggy.data.repository

import app.android.doggy.data.remote.model.BreedImagesResponse
import app.android.doggy.data.remote.network.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetBreedImagesRepositoryImplTest {

    private val mockApiService: ApiService = mockk()
    private val repository = GetBreedImagesRepositoryImpl(mockApiService)

    @Test
    fun `when getting dog breed images response, then return list of images`() {
        // Given
        val breedName = "akita"
        val breedImagesResponse = BreedImagesResponse(
            status = "success",
            message = listOf(
                "https://images.dog.ceo/breeds/akita/512px-Ainu-Dog.jpg",
                "https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpg"
            ),
            code = 200
        )
        coEvery { mockApiService.getBreedImages(breedName) } returns breedImagesResponse

        // When
        val result = runBlocking { repository.getList(breedName) }

        // Then
        assertEquals(2, result.size)
    }
}
