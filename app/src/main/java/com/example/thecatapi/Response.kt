package com.example.thecatapi

sealed class Response<T>(val data: T?, val message: String?) {

    class Success<T>(data: T): Response<T>(data, null)

    class Error<T>(message: String): Response<T>(null, message)
}