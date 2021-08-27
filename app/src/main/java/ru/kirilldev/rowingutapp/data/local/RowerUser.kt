package ru.kirilldev.rowingutapp.data.local



data class RowerUser(
    val id: String,
    val name: String,
    val hash: String,
    val email: String,
    val age: String,
    val weight: String,
    val height: String,
    val calories: Int,
    val averageBpm: Int,
    val ratingPlace: Int
)



