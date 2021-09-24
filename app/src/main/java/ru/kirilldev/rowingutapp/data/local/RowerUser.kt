package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import java.util.*


@Entity(
    tableName = "rower_table"
)
data class RowerUser(

    @ColumnInfo(name = "rower_id")
    var id: String = UUID.randomUUID().toString(),

    var name: String? = null,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "rower_email")
    var email: String = "",

    var img: String? = null,

    var age: Int? = null,

    var weight: Float? = null,

    var height: Float? = null,

    var averageCalories: Int = 0,

    var averageBpm: Int = 0,

    @Ignore
    var currentRatingPlace: Int = 0,

    @Ignore
    var totalCalories: Int = 0,

    @Ignore
    var oldRatingPlace: Int = 0,

    @Ignore
    var isRatingChange: Boolean = false,

    //for ui
    @Ignore
    var statisticItem: Int = 0


)



