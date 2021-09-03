package ru.kirilldev.rowingutapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "training_table", indices = [Index(value = ["training_date"], unique = true)])
data class Training(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "training_date")
    val trainingDate: String? = null,

    @ColumnInfo(name = "name")
    val trainingName: String? = null,

    @ColumnInfo(name = "time")
    val trainingTime: Long? = null,

    @ColumnInfo(name = "tasks")
    val trainingTasks: List<String>? = emptyList(),

    @ColumnInfo(name = "type")
    val trainingType: String? = null
)