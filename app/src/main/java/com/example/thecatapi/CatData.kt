package com.example.thecatapi

@kotlinx.serialization.Serializable
data class CatData(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)