package com.techenthusis.learningsession.di

import com.techenthusis.learningsession.data.NetworkBuilder
import com.techenthusis.learningsession.data.repositry.CatFactRepoImpl
import com.techenthusis.learningsession.domain.repositry.CatFactRepository
import com.techenthusis.learningsession.domain.usecases.GetRandomCatFactUseCase
import com.techenthusis.learningsession.presentation.viewmodel.CatFactViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { NetworkBuilder.createCatFactAPiService() }
    single<CatFactRepository> { CatFactRepoImpl(get()) }
    single { GetRandomCatFactUseCase(get()) }
    viewModel { CatFactViewModel(get()) }
}
