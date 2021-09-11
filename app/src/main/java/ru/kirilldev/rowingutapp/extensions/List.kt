package ru.kirilldev.rowingutapp.extensions

import ru.kirilldev.rowingutapp.data.remote.RowerRank

// need to check user totalCalories and sort list by maximum of calories
fun List<RowerRank>.getSortedRowerRankList(): List<RowerRank>{

    var maxCalories = 0
    var previousRower: RowerRank

    this.forEachIndexed { index, rowerRank ->
        if(rowerRank.totalCalories > maxCalories){

            maxCalories = rowerRank.totalCalories

            previousRower = rowerRank



        }
    }

    return this
}