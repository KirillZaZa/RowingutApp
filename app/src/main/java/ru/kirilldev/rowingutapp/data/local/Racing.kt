package ru.kirilldev.rowingutapp.data.local

data class Racing(
    val racingDate: String,
    val racingPlace: String,
    val racingListInfo: List<RacingInfo>,
    val racingCount: Int = racingListInfo.size
)


data class RacingInfo(
    val racingInfoMeters: Int,
    val racingInfoType: String
)
