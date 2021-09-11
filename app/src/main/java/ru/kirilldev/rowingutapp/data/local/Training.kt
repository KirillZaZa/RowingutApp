package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import ru.kirilldev.rowingutapp.room.typeconverter.ListTypeConverter


@Entity(tableName = "training_table", indices = [Index(value = ["training_date"], unique = true)])
data class Training(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "training_date")
    val trainingDate: String? = null,

    @ColumnInfo(name = "name")
    val trainingName: String? = null,

    @ColumnInfo(name = "time")
    val trainingTime: Float? = null,

    @ColumnInfo(name = "tasks")
    @TypeConverters(ListTypeConverter::class)
    val trainingTasks: List<String>? = emptyList(),

    @ColumnInfo(name = "type")
    val trainingType: String? = null,

    //fields needed for ui work
    @Ignore
    var isStarted: Boolean = false,
    @Ignore
    var currentTime: String? = null
)