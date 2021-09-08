package ru.kirilldev.rowingutapp.repository.interfaces

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training

interface IRowingutRepository {

    fun loadUserData(id: String): LiveData<RowerUser?>

    fun uploadUserData(rowerUser: RowerUser)

    fun loadRowerList(): LiveData<List<RowerUser>?>

    fun updateRowerUser(user: RowerUser)

    fun uploadTrainingData(training: Training)

    fun loadLastTrainings(): LiveData<List<Training>?>

    fun loadTraining(date: String): LiveData<Training?>

    fun updateTraining(training: Training)

    fun loadRacingListData(): LiveData<List<Racing>?>

    fun loadRacing(date: String): LiveData<Racing?>

    fun deleteTraining(date: String, callback: (Boolean) -> Unit)
}