package com.techenthusis.learningsession.domain.usecases

import com.techenthusis.learningsession.domain.model.CatFact
import com.techenthusis.learningsession.domain.model.Result
import com.techenthusis.learningsession.domain.repositry.CatFactRepository

class GetRandomCatFactUseCase(private val catFactRepository: CatFactRepository) {
    suspend operator fun invoke(): Result<CatFact> = catFactRepository.getCatFact()
}