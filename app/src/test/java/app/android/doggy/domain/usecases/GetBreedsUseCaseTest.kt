package app.android.doggy.domain.usecases

import app.android.doggy.data.remote.model.Breed
import app.android.doggy.domain.repository.GetBreedsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetBreedsUseCaseTest {

    private val mockRepository: GetBreedsRepository = mockk()
    private val useCase = GetBreedsUseCase(mockRepository)

    @Test
    fun `when invoking breeds use case, then emit list of breeds`() = runTest {
        // Given
        val breeds = listOf(Breed("akita"), Breed("hound"))
        coEvery { mockRepository.getList() } returns breeds

        // When
        useCase.invoke().onEach {
            // Then
            assertEquals(breeds, it.data)
        }
    }

    @Test
    fun `when invoking breeds use case, then emit error`() = runTest {
        // Given
        val errorMessage = "Error fetching breeds"
        coEvery { mockRepository.getList() } throws Exception(errorMessage)

        // When
        useCase.invoke().onEach {
            // Then
            assertEquals(errorMessage, it.message)
        }
    }
}
