package com.example.domain

sealed class RequestResult<out T> {
    data class Success<out T>(val data: T): RequestResult<T>()
    data class Error(val error: Exception): RequestResult<Nothing>()
    data object Loading: RequestResult<Nothing>()
}