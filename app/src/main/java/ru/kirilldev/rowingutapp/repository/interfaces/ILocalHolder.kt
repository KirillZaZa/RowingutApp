package ru.kirilldev.rowingutapp.repository.interfaces

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training

interface ILocalHolder {


    fun getUserData(id: String): LiveData<RowerUser?>

    fun putUserData(rowerUser: RowerUser)

    fun getRowerList(): LiveData<List<RowerUser>?>

    fun updateRowerUser(user: RowerUser)

    fun deleteRowerUser(user: RowerUser)

    fun putTrainingData(training: Training)

    fun getLastTrainings(): LiveData<List<Training>?>

    fun getTraining(date: String): LiveData<Training?>

    fun updateTraining(training: Training)

    fun deleteTraining(date: String)

    fun putRacingListData(racing: Racing)

    fun getRacingListData(): LiveData<List<Racing>?>

    fun getRacing(date: String): LiveData<Racing?>

    fun updateRacing(racing: Racing)

    fun deleteRacing(racing: Racing)



}