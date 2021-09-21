package ru.kirilldev.rowingutapp.extensions

import ru.kirilldev.rowingutapp.data.remote.RowerRank

fun List<RowerRank>.getSortedRowerRankList(): List<RowerRank> {
    return sortedBy { it.totalCalories }
}



