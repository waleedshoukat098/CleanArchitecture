package com.techenthusis.learningsession.data

import com.techenthusis.learningsession.data.core.utils.Constants
import com.techenthusis.learningsession.data.remote.api.CatFactApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {
    private const val BASE_URL = Constants.URL
    fun createCatFactAPiService(): CatFactApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CatFactApiService::class.java)
    }
}