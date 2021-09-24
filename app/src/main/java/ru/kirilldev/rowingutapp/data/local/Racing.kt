package ru.kirilldev.rowingutapp.data.local

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "racing_table", indices = [Index(value = ["racing_date"], unique = true)])
data class Racing(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "racing_date")
    var racingDate: String = "",

    var racingPlace: String? = null,

    @ColumnInfo(name = "racing_meters")
    var racingInfoMeters: Int? = 0,

    @ColumnInfo(name = "racing_type")
    var racingInfoType: String? = null,


    //for ui
    @Ignore
    var openedItemIndex: Int? = null,
    @Ignore
    var itemDate: String? = null,
    @Ignore
    var itemRacings: List<RacingInfo>? = null

)

data class RacingInfo(
    var racingInfoMeters: Int? = 0,

    var racingInfoType: String? = null,
): Serializable{

}
