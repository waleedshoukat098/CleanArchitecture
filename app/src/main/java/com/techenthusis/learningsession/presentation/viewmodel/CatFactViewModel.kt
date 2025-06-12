package com.techenthusis.learningsession.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techenthusis.learningsession.domain.model.CatFact
import com.techenthusis.learningsession.domain.model.Result
import com.techenthusis.learningsession.domain.usecases.GetRandomCatFactUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatFactViewModel(private val getRandomCatFactUseCase: GetRandomCatFactUseCase) : ViewModel() {

    private val _catFact = MutableStateFlow<CatFact?>(null)
    val catFact: StateFlow<CatFact?> = _catFact

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    fun fetchRandomCat() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            _catFact.value = null
            when (val result = getRandomCatFactUseCase()) {
                is Result.Error -> {
                    val message = result.exception.localizedMessage ?: "An unknown error occurred"
                    _errorMessage.value = "Error: $message"
                    _isLoading.value = false
                    result.exception.printStackTrace()

                }

                is Result.Success<*> -> {
                    _isLoading.value = false
                    _catFact.value = result.data as CatFact
                }
            }

        }
    }

    fun errorMessageShown() {
        _errorMessage.value = null
    }

}