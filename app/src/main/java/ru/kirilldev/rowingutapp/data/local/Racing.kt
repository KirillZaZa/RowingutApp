package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "racing_table", indices = [Index(value = ["racing_date"], unique = true)])
data class Racing(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "racing_date")
    val racingDate: String,

    val racingPlace: String,


    @Embedded
    val racingListInfo: RacingInfo,

): Serializable{

}

@Entity(tableName = "racing_info")
data class RacingInfo(
    val racingInfoMeters: Int,

    val racingInfoType: String
): Serializable{

}
