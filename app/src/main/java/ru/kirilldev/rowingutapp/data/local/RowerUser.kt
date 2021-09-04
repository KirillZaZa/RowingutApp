package ru.kirilldev.rowingutapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "rower_table",
    indices = [Index(value = ["rower_id", "rower_email"], unique = true)]
)
data class RowerUser(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "rower_id")
    val id: String,

    val name: String,

    val hash: String,

    @ColumnInfo(name = "rower_email")
    val email: String,

    val img: String? = null,

    val age: Int? = null,

    val weight: Float? = null,

    val height: Float? = null,

    val calories: Int = 0,

    val averageBpm: Int = 0,

    val ratingPlace: Int = 0
)



