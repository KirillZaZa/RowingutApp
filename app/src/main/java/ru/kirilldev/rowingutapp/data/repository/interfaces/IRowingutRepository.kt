package ru.kirilldev.rowingutapp.data.repository.interfaces

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.remote.RowerRank

interface IRowingutRepository {

    fun signIn(email: String, password: String, callback: (String) -> Unit)

    fun signUp(email: String, password: String, callback: (String) -> Unit)

    fun updateEntrySettings(entrySettings: EntrySettings)

    fun getEntrySettings(): LiveData<EntrySettings>

    fun putRowerRank(rowerRank: RowerRank)

    fun signOut(callback: (Unit) -> Unit)

    fun loadUserData(): LiveData<RowerUser?>

    fun uploadUserData(rowerUser: RowerUser,isSucceeded: (Boolean) -> Unit)

    fun loadRowerRankList(): LiveData<List<RowerRank>?>

    fun updateRowerUser(user: RowerUser, isSucceeded: (Boolean) -> Unit)

    fun uploadTrainingData(training: Training)

    fun loadLastTrainings(): LiveData<List<Training>?>

    fun loadTraining(date: String): LiveData<Training?>

    fun updateTraining(training: Training)

    fun loadRacingListData(): LiveData<List<Racing>?>

    fun loadRacing(date: String): LiveData<Racing?>

    fun deleteTraining(training: Training, callback: (Boolean) -> Unit)

}