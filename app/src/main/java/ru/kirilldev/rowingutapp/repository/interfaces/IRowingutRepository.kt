package ru.kirilldev.rowingutapp.repository.interfaces

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.remote.RowerRank

interface IRowingutRepository {

    fun loadUserData(): LiveData<RowerUser?>

    fun uploadUserData(rowerUser: RowerUser)

    fun loadRowerRankList(): LiveData<List<RowerRank>?>

    fun updateRowerUser(user: RowerUser)

    fun uploadTrainingData(training: Training)

    fun loadLastTrainings(): LiveData<List<Training>?>

    fun loadTraining(date: String): LiveData<Training?>

    fun updateTraining(training: Training)

    fun loadRacingListData(): LiveData<List<Racing>?>

    fun loadRacing(date: String): LiveData<Racing?>

    fun deleteTraining(date: String, callback: (Boolean) -> Unit)
}