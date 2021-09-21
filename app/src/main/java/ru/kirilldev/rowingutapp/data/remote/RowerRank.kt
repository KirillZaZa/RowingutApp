package ru.kirilldev.rowingutapp.data.remote

import android.graphics.Bitmap


data class RowerRank(
    val id: String? = null,

    val name: String? = null,

    val img: String? = null,

    val currentRatingPlace: Int = 0,

    val totalCalories: Int = 0,

    val oldRatingPlace: Int = 0,

    val isRatingChange: Boolean = false,

    //ui
    var imageBitmap: Bitmap? = null
)
