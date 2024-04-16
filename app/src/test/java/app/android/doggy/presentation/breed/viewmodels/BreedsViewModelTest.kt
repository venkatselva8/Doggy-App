package app.android.doggy.presentation.breed.viewmodels

import app.android.doggy.core.common.Resource
import app.android.doggy.data.remote.model.Breed
import app.android.doggy.domain.usecases.GetBreedsUseCase
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
class BreedsViewModelTest {

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
    fun `when fetching breeds, then emit list of breeds`() {
        // Given
        val breeds = listOf(Breed("bulldog"), Breed("akita"))
        val mockUseCase: GetBreedsUseCase = mockk()
        coEvery { mockUseCase() } returns flow { emit(Resource.Success(breeds)) }
        val viewModel = BreedsViewModel(mockUseCase)

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedsList()

            // Then
            assertEquals(breeds, viewModel.breedsStateHolder.value.breeds)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }

    @Test
    fun `when fetching breeds, then emit error`() {
        // Given
        val errorMessage = "Error fetching breeds"
        val mockUseCase: GetBreedsUseCase = mockk()
        coEvery { mockUseCase() } returns flow { emit(Resource.Error(errorMessage)) }
        val viewModel = BreedsViewModel(mockUseCase)

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedsList()

            // Then
            assertEquals(errorMessage, viewModel.breedsStateHolder.value.error)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }

    @Test
    fun `when fetching breeds, then show progress bar`() {
        // Given
        val mockUseCase: GetBreedsUseCase = mockk()
        coEvery { mockUseCase() } returns flow { emit(Resource.Loading()) }
        val viewModel = BreedsViewModel(mockUseCase)

        // When
        val job = CoroutineScope(testDispatcher).launch {
            viewModel.getDogBreedsList()

            // Then
            assertEquals(true, viewModel.breedsStateHolder.value.isLoading)
        }
        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()
    }
}

