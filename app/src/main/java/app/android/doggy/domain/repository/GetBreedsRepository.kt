package app.android.doggy.domain.repository

import app.android.doggy.data.remote.model.Breed

interface GetBreedsRepository {
    suspend fun getList(): List<Breed>
}