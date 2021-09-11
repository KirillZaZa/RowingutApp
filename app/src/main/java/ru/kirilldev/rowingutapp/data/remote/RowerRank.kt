package ru.kirilldev.rowingutapp.data.remote


/**
 *
 * Need to add photo field
 */

data class RowerRank(
    val id: String? = null,

    val name: String? = null,

    val currentRatingPlace: Int = 0,

    val totalCalories: Int = 0,

    val oldRatingPlace: Int = 0,

    val isRatingChange: Boolean = false
)
