package com.techenthusis.learningsession.data.repositry

import com.techenthusis.learningsession.data.model.toDomain
import com.techenthusis.learningsession.data.remote.api.CatFactApiService
import com.techenthusis.learningsession.domain.model.CatFact
import com.techenthusis.learningsession.domain.model.Result
import com.techenthusis.learningsession.domain.repositry.CatFactRepository

class CatFactRepoImpl(private val catFactApiService: CatFactApiService) : CatFactRepository {
    override suspend fun getCatFact(): Result<CatFact> {
        return try {
            val catFactDTO = catFactApiService.getRandomFact()
            Result.Success(catFactDTO.toDomain())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}