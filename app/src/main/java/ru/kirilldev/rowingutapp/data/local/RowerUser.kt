package ru.kirilldev.rowingutapp.data.local



data class RowerUser(
    val id: String,
    val name: String,
    val hash: String,
    val email: String,
    val img: String? = null,
    val age: Int? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val calories: Int = 0,
    val averageBpm: Int = 0,
    val ratingPlace: Int = 0
)



