package app.android.doggy.di

import app.android.doggy.core.utils.Constants
import app.android.doggy.data.remote.network.ApiService
import app.android.doggy.data.repository.GetBreedImagesRepositoryImpl
import app.android.doggy.data.repository.GetBreedsRepositoryImpl
import app.android.doggy.domain.repository.GetBreedImagesRepository
import app.android.doggy.domain.repository.GetBreedsRepository
import app.android.doggy.domain.usecases.GetBreedImagesUseCase
import app.android.doggy.domain.usecases.GetBreedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providesGetBreedsRepository(apiService: ApiService) : GetBreedsRepository {
        return GetBreedsRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetBreedImagesRepository(apiService: ApiService): GetBreedImagesRepository {
        return GetBreedImagesRepositoryImpl(apiService)
    }

    @Provides
    fun providesGetBreedsUseCase(getBreedsRepository: GetBreedsRepository) : GetBreedsUseCase {
        return GetBreedsUseCase(getBreedsRepository)
    }

    @Provides
    fun providesGetBreedImagesUseCase(getBreedImagesRepository: GetBreedImagesRepository) : GetBreedImagesUseCase {
        return GetBreedImagesUseCase(getBreedImagesRepository)
    }
}