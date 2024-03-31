package app.android.doggy.usecase

import app.android.doggy.model.DogBreedImagesResponse
import app.android.doggy.model.DogBreedResponse
import app.android.doggy.network.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class DogBreedsUseCaseTest {

    private val mockApiService: ApiService = mockk()
    private val dogBreedsUseCase = DogBreedsUseCase(mockApiService)

    @Test
    fun `getBreedsList should return list of dog breeds`() = runBlockingTest {
        // Given
        val response = DogBreedResponse("success", mapOf("breed1" to emptyList()), 200)
        coEvery { mockApiService.getBreedsList() } returns response

        // When
        val result = dogBreedsUseCase.getBreedsList()

        // Then
        assertEquals(1, result?.size)
        assertEquals("breed1", result?.get(0)?.name)
    }

    @Test
    fun `getBreedImages should return list of breed images`() = runBlockingTest {
        // Given
        val breedName = "breed1"
        val response = DogBreedImagesResponse("success", listOf("image1", "image2"), 200)
        coEvery { mockApiService.getBreedImages(breedName) } returns response

        // When
        val result = dogBreedsUseCase.getBreedImages(breedName)

        // Then
        assertEquals(2, result?.size)
        assertEquals("image1", result?.get(0))
        assertEquals("image2", result?.get(1))
    }
}
