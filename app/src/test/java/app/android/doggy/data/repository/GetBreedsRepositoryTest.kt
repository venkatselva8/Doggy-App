package app.android.doggy.data.repository

import app.android.doggy.data.remote.model.BreedsResponse
import app.android.doggy.data.remote.network.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetBreedsRepositoryTest {

    private val mockApiService: ApiService = mockk()
    private val repository = GetBreedsRepositoryImpl(mockApiService)

    @Test
    fun `when getting dog breeds list response, then return breeds`() {
        // Given
        val breedsResponse = BreedsResponse(
            status = "success",
            message = mapOf(
                "hound" to listOf("afghan", "basset"),
                "akita" to listOf()
            ),
            code = 200
        )
        coEvery { mockApiService.getBreedsList() } returns breedsResponse

        // When
        val result = runBlocking { repository.getList() }

        // Then
        assertEquals(2, result.size)
        assertEquals("hound", result[0].name)
        assertEquals("akita", result[1].name)
    }
}
