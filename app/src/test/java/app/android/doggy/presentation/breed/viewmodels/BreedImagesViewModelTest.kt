package app.android.doggy.presentation.breed.viewmodels

import androidx.lifecycle.SavedStateHandle
import app.android.doggy.core.common.Resource
import app.android.doggy.domain.usecases.GetBreedImagesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BreedImagesViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `when fetching breed images, then emit list of images`() {
        // Given
        val breedName = "akita"
        val breedImages = listOf(
            "https://images.dog.ceo/breeds/akita/512px-Ainu-Dog.jpg",
            "https://images.dog.ceo/breeds/akita/512px-Akita_inu.jpg"
        )
        val mockUseCase: GetBreedImagesUseCase = mockk()
        coEvery { mockUseCase(breedName) } returns flow { emit(Resource.Success(breedImages)) }
        val viewModel = BreedImagesViewModel(mockUseCase, SavedStateHandle())

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedImages(breedName)

            // Then
            assertEquals(breedImages, viewModel.breedImagesStateHolderHolder.value.breedImages)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }

    @Test
    fun `when fetching breed images, then emit error`() {
        // Given
        val breedName = "akita"
        val errorMessage = "Error fetching breed images"
        val mockUseCase: GetBreedImagesUseCase = mockk()
        coEvery { mockUseCase(breedName) } returns flow { emit(Resource.Error(errorMessage)) }
        val viewModel = BreedImagesViewModel(mockUseCase, SavedStateHandle())

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedImages(breedName)

            // Then
            assertEquals(errorMessage, viewModel.breedImagesStateHolderHolder.value.error)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }

    @Test
    fun `when fetching breed images, then show progress bar`() {
        // Given
        val breedName = "akita"
        val mockUseCase: GetBreedImagesUseCase = mockk()
        coEvery { mockUseCase(breedName) } returns flow { emit(Resource.Loading()) }
        val viewModel = BreedImagesViewModel(mockUseCase, SavedStateHandle())

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedImages(breedName)

            // Then
            assertEquals(true, viewModel.breedImagesStateHolderHolder.value.isLoading)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }
}

