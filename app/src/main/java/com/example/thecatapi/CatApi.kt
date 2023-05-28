package com.example.thecatapi

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

class CatApi {

    private val client = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun load(num: String): Response<List<CatData>> {
        return try {
            val data = client.get<List<CatData>>(
                Url("https://api.thecatapi.com/v1/images/search?limit=$num")
            )
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error")
        }
    }
}