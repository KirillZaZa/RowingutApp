package ru.kirilldev.rowingutapp.data.repository

import androidx.lifecycle.LiveData
import ru.kirilldev.rowingutapp.data.firebase.auth.Authentication
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.prefs.PrefManager
import ru.kirilldev.rowingutapp.data.remote.RowerRank
import ru.kirilldev.rowingutapp.data.repository.holder.FirebaseDataHolder
import ru.kirilldev.rowingutapp.data.repository.holder.LocalDataHolder
import ru.kirilldev.rowingutapp.data.repository.interfaces.IRowingutRepository

class RowingutRepository(
    private val firebase: FirebaseDataHolder = FirebaseDataHolder,
    private val db: LocalDataHolder = LocalDataHolder,
    private val auth: Authentication = Authentication(),
    private val prefsManager: PrefManager = PrefManager()
) : IRowingutRepository {


    override fun signIn(email: String, password: String, callback: (String) -> Unit) {
        auth.signIn(email, password){
            callback(it)
            // prefsManager -> isSignedIn -> true
            // prefsManager -> userEmail -> _userMail@
        }
    }

    override fun signUp(email: String, password: String, callback: (String) -> Unit) {
        auth.signUp(email, password){
            callback(it)
            // prefsManager -> isSignedIn -> true
            // prefsManager -> userEmail -> _userMail@
        }
    }

    override fun isSignedIn(callback: (Boolean) -> Unit) {
        // if user null -> prefsManager -> isSignedIn -> false
        // not null -> prefsManager -> isSignedIn -> true
        // callback(isSignedIn)
    }

    override fun updateFirstEntry() {
        //prefsManager -> isFirstEntry = !isFirstEntry
    }

    override fun isFirstEntry(): Boolean {
        //prefsManager -> isFirstEntry
        return false
    }

    override fun putRowerRank(rowerRank: RowerRank) {
        firebase.putRowerRank(rowerRank)
    }

    override fun signOut(callback: (Unit) -> Unit) {
        //prefs -> isSignedIn -> false
        callback(auth.signOut())
    }

    override fun loadUserData(): LiveData<RowerUser?> {
        val email = auth.getCurrentUser().email!!
        firebase.getRowerUser(email){
            db.putUserData(it.value!!)
        }
        return db.getUserData(email)
    }

    override fun uploadUserData(rowerUser: RowerUser, isSucceeded: (Boolean) -> Unit) {
        firebase.putRowerUser(rowerUser){
            if(it) db.putUserData(rowerUser)
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
        firebase.putTraining(training) {
            if (it) db.putTrainingData(training)
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
        firebase.updateTodayTraining(training) { isSucceeded ->
            if (isSucceeded) db.updateTraining(training)
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
        firebase.deleteTraining(date) {
            if(it){
                db.deleteTraining(date)
                success = true
            }
        }

        callback(success)
    }


}
