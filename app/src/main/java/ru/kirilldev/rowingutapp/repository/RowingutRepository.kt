package ru.kirilldev.rowingutapp.repository

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.repository.holder.FirebaseDataHolder
import ru.kirilldev.rowingutapp.repository.holder.LocalDataHolder
import ru.kirilldev.rowingutapp.repository.interfaces.IRowingutRepository

class RowingutRepository(
    private val firebase: FirebaseDataHolder = FirebaseDataHolder,
    private val db: LocalDataHolder = LocalDataHolder

) : IRowingutRepository {

    override fun loadUserData(id: String): LiveData<RowerUser?> {
        return db.getUserData(id)
    }

    override fun uploadUserData(rowerUser: RowerUser) {
        firebase.putRowerUser(rowerUser)
    }

    override fun loadRowerList(): LiveData<List<RowerUser>?> {
        return firebase.getRowerUserList()
    }

    override fun updateRowerUser(user: RowerUser) {
        firebase.updateRowerUser(user)
    }

    override fun uploadTrainingData(training: Training) {
        firebase.updateTodayTraining(training)
        db.putTrainingData(training)
    }

    override fun loadLastTrainings(): LiveData<List<Training>?> {
        return db.getLastTrainings()
    }

    override fun loadTraining(date: String): LiveData<Training?> {
        return db.getTraining(date)
    }

    override fun updateTraining(training: Training) {
        firebase.updateTodayTraining(training)
        db.updateTraining(training)
    }

    override fun loadRacingListData(): LiveData<List<Racing>?> {
        val racingData = firebase.getRacingsList()
        racingData.value?.forEach { racing->
            db.putRacingListData(racing)
        }

        return racingData
    }

    override fun loadRacing(date: String): LiveData<Racing?> {
        return db.getRacing(date)
    }



    /**
     * @RowingutRepository implements methods for getting data from DataHolder
     *
     */

}