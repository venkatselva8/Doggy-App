package app.android.doggy.domain.usecases

import app.android.doggy.domain.repository.GetBreedImagesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetBreedImagesUseCaseTest {

    private val mockRepository: GetBreedImagesRepository = mockk()
    private val useCase = GetBreedImagesUseCase(mockRepository)

    @Test
    fun `when invoking breed images use case, then emit list of images`() = runTest {
        // Given
        val breedName = "akita"
        val breedImages = listOf(
            "https://images.dog.ceo/breeds/akita/512px-Ainu-Dog.jpg",
            "https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpg"
        )
        coEvery { mockRepository.getList(breedName) } returns breedImages

        // When
        useCase.invoke(breedName).onEach {
            // Then
            assertEquals(breedImages, it.data)
        }
    }

    @Test
    fun `when invoking breeds use case, then emit error`() = runTest {
        // Given
        val breedName = "akita"
        val errorMessage = "Error fetching breed images"
        coEvery { mockRepository.getList(breedName) } throws Exception(errorMessage)

        // When
        useCase.invoke(breedName).onEach {
            // Then
            assertEquals(errorMessage, it.message)
        }
    }
}
