package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import java.util.*


@Entity(
    tableName = "rower_table",
    indices = [Index(value = ["rower_id", "rower_email"], unique = true)]
)
data class RowerUser(

    @ColumnInfo(name = "rower_id")
    val id: String = UUID.randomUUID().toString(),

    val name: String? = null,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "rower_email")
    val email: String? = null,

    val img: String? = null,

    val age: Int? = null,

    val weight: Float? = null,

    val height: Float? = null,

    val averageCalories: Int = 0,

    val averageBpm: Int = 0,

    @Ignore
    val currentRatingPlace: Int = 0,

    @Ignore
    val totalCalories: Int = 0,

    @Ignore
    val oldRatingPlace: Int = 0,

    @Ignore
    val isRatingChange: Boolean = false,

    //for ui
    @Ignore
    val statisticItem: Int = 0


)



