package com.techenthusis.learningsession.domain.model

data class CatFact(
    val fact: String,
    val length: Int
)

//i will check

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}