package ru.kirilldev.rowingutapp.data.local

import java.io.Serializable

data class Racing(
    val racingDate: String,
    val racingPlace: String,
    val racingListInfo: List<RacingInfo>,
    val racingCount: Int = racingListInfo.size
): Serializable{

}


data class RacingInfo(
    val racingInfoMeters: Int,
    val racingInfoType: String
): Serializable{

}
