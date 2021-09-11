package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "racing_table", indices = [Index(value = ["racing_date"], unique = true)])
data class Racing(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "racing_date")
    val racingDate: String? = null,

    val racingPlace: String? = null,


    @Embedded
    val racingListInfo: List<RacingInfo>? = null,


    //for ui
    @Ignore
    val openedItemIndex: Int? = null,
    @Ignore
    val itemDate: String? = null,
    @Ignore
    val itemRacings: List<RacingInfo>? = null

): Serializable{

}

@Entity(tableName = "racing_info")
data class RacingInfo(
    val racingInfoMeters: Int,

    val racingInfoType: String
): Serializable{

}
