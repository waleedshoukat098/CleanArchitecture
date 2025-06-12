package com.techenthusis.learningsession.data.remote.api

import com.techenthusis.learningsession.data.model.CatFactDTO
import retrofit2.http.GET

interface CatFactApiService {
    @GET("facts/random")
    suspend fun getRandomFact(): CatFactDTO
}