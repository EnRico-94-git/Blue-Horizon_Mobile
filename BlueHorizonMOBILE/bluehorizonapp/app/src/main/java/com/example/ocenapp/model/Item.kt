package com.example.ocenapp.model

data class Item(
    val id: String,
    val name: String,
    val pollutionLevel: Int,
    val temperature: Float,
    val biodiversityIndex: Float
)
