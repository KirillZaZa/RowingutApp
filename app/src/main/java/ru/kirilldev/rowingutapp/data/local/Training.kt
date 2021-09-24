package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import ru.kirilldev.rowingutapp.data.room.typeconverter.ListTypeConverter

@Entity(tableName = "training_table")
data class Training(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "training_date")
    val trainingDate: String,

    @ColumnInfo(name = "training_name")
    val trainingName: String,

    @ColumnInfo(name = "training_time")
    val trainingTime: Float? = 0f,

    @field:TypeConverters(ListTypeConverter::class)
    val trainingTasks: List<String> = listOf(),


    @ColumnInfo(name = "training_type")
    val trainingType: String,

    @Ignore
    var isStarted: Boolean = false,
    @Ignore
    var currentTime: String? = null
)