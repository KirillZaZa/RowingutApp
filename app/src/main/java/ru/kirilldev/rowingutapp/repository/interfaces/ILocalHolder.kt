package ru.kirilldev.rowingutapp.repository.interfaces

import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training

interface ILocalHolder {


    fun putRacingListData(list: List<Racing>)

    fun putUserData(rowerUser: RowerUser)

    fun putTrainingData(training: Training)

    fun getLastTrainings(): List<Training>

    fun getUserData(id: String): RowerUser

    fun getRacingListData(list: List<Racing>): List<Racing>




}