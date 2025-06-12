package com.techenthusis.learningsession.domain.repositry

import com.techenthusis.learningsession.domain.model.CatFact
import com.techenthusis.learningsession.domain.model.Result

interface CatFactRepository {
    suspend fun getCatFact(): Result<CatFact>
}