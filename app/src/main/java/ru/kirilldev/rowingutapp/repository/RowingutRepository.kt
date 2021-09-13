package ru.kirilldev.rowingutapp.repository

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.remote.RowerRank
import ru.kirilldev.rowingutapp.repository.holder.FirebaseDataHolder
import ru.kirilldev.rowingutapp.repository.holder.LocalDataHolder
import ru.kirilldev.rowingutapp.repository.interfaces.IRowingutRepository

class RowingutRepository(
    private val firebase: FirebaseDataHolder = FirebaseDataHolder,
    private val db: LocalDataHolder = LocalDataHolder
    // prefs manager
) : IRowingutRepository {

    override fun loadUserData(): LiveData<RowerUser?> {
        val id = "" // get id in prefs

        return db.getUserData(id)
    }

    override fun uploadUserData(rowerUser: RowerUser, isSucceeded: (Boolean) -> Unit) {
        firebase.putRowerUser(rowerUser){
            isSucceeded(it)
        }
    }


    override fun loadRowerRankList(): LiveData<List<RowerRank>?> {
        return firebase.getRowerRankList()
    }


    override fun updateRowerUser(user: RowerUser, isSucceeded: (Boolean)-> Unit) {
        firebase.updateRowerUser(user){
            isSucceeded(it)
        }
    }

    override fun uploadTrainingData(training: Training) {
        firebase.putTraining(training) { isSucceded ->
            if (isSucceded) db.putTrainingData(training)
        }
    }

    override fun loadLastTrainings(): LiveData<List<Training>?> {
        return db.getLastTrainings()
    }

    override fun loadTraining(date: String): LiveData<Training?> {
        firebase.getTodayTraining { training ->
            if (training.value != null) {
                db.putTrainingData(training.value!!)
            }
        }
        return db.getTraining(date)
    }

    override fun updateTraining(training: Training) {
        firebase.updateTodayTraining(training) { isSucceed ->
            if (isSucceed) db.updateTraining(training)
        }
    }

    override fun loadRacingListData(): LiveData<List<Racing>?> {
        val racingData = firebase.getRacingsList()
        racingData.value?.forEach { racing ->
            db.putRacingListData(racing)
        }

        return racingData
    }

    override fun loadRacing(date: String): LiveData<Racing?> {
        return db.getRacing(date)
    }

    override fun deleteTraining(date: String, callback: (Boolean) -> Unit) {
        var success = false
        firebase.deleteTraining(date) { isSucceeded ->
            if(isSucceeded){
                db.deleteTraining(date)
                success = true
            }
        }

        callback(success)
    }


    /**
     * @RowingutRepository implements methods for getting data from DataHolder
     *
     */

}