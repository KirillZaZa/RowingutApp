package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import ru.kirilldev.rowingutapp.data.room.typeconverter.ListTypeConverter
import ru.kirilldev.rowingutapp.utils.DateUtil

@Entity(tableName = "training_table")
data class Training(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "training_date")
    var trainingDate: String = DateUtil.getDate(),

    @ColumnInfo(name = "training_name")
    var trainingName: String? = null,

    @ColumnInfo(name = "training_time")
    var trainingTime: Float? = 0f,

    @field:TypeConverters(ListTypeConverter::class)
    var trainingTasks: List<String> = listOf(),


    @ColumnInfo(name = "training_type")
    var trainingType: String? = null,

    @Ignore
    var isStarted: Boolean = false,
    @Ignore
    var currentTime: String? = null
)