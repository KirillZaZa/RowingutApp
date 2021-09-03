package ru.kirilldev.rowingutapp.repository.holder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.api.retrofit.RetrofitInstance
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.repository.interfaces.ILocalHolder
import java.util.*

object FirebaseDataHolder {


    /**
     *
     * @FirebaseDataHolder is intended for network interaction
     *
     */

    private const val FIREBASE_TAG = "firebase_tag"
    private val scope = RowingutApplication.scope
    private val api = RetrofitInstance.api

    /*
    TODO: Need to finish the realisation of network and local contribution with data
     */

    /**
     * getting training of the day
     *
     */
    fun getTodayTraining(): MutableLiveData<Training?> {
        val trainingLiveData = MutableLiveData<Training?>()

        val trainingJob = scope.launch {
            try {
                val response: Training
                withContext(Dispatchers.IO) {
                    response = api.getTodayTraining().await()
                }
                trainingLiveData.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                trainingLiveData.value = null
            }
        }
        if (trainingJob.isCompleted) {
            trainingJob.cancel()
        }

        return trainingLiveData
    }


    /**
     *
     *
     */
    fun getRacingsList(): MutableLiveData<List<Racing>?> {
        val racingsLiveData = MutableLiveData<List<Racing>?>()

        val racingJob = scope.launch {
            try {
                val response: List<Racing>?
                withContext(Dispatchers.IO) {
                    response = api.getRacingsList().await()
                }
                racingsLiveData.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                racingsLiveData.value = null
            }
        }

        if (racingJob.isCompleted) {
            racingJob.cancel()
        }

        return racingsLiveData
    }

    fun getRowerUserList(): MutableLiveData<List<RowerUser>?> {
        val userList = MutableLiveData<List<RowerUser>?>()

        val rowerListJob = scope.launch {
            try {
                val response: List<RowerUser>?

                withContext(Dispatchers.IO) {
                    response = api.getRowerUserList().await()
                }
                userList.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                userList.value = null
            }
        }

        if (rowerListJob.isCompleted) {
            rowerListJob.cancel()
        }


        return userList
    }

    fun getRowerUser(id: String): MutableLiveData<RowerUser?> {
        val rowerUser = MutableLiveData<RowerUser?>()

        val userJob = scope.launch {
            try {
                val response: RowerUser?
                withContext(Dispatchers.IO) {
                    response = api.getRowerUser(id).await()
                }
                rowerUser.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                rowerUser.value = null
            }
        }

        if (userJob.isCompleted) userJob.cancel()

        return rowerUser
    }

    fun updateRowerUser(id: String, newUser: RowerUser, isSuccessful: (Boolean) -> Unit){
        var isSucceeded = false

        val updateRowerJob = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.updateRowerUser(id, newUser)
                }
                isSucceeded = true
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                isSuccessful(isSucceeded)
            }
        }
        if (updateRowerJob.isCompleted) updateRowerJob.cancel()

        isSuccessful(isSucceeded)
    }

    fun updateTodayTraining(date: Date, newTraining: Training, isSuccessful: (Boolean) -> Unit) {
        var isSucceeded = false

        val updateTrainingJob = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.updateTodayTraining(date.toString(), newTraining)
                }
                isSucceeded = true
            } catch (e: CancellationException) {
                isSuccessful(isSucceeded)
            }
        }
        if (updateTrainingJob.isCompleted) updateTrainingJob.cancel()


        isSuccessful(isSucceeded)
    }


}


object LocalDataHolder : ILocalHolder {

    /**
     * @LocalDataHolder is intended for interaction with local storage
     *
     * Here is the interaction with the database Room
     *
     */

    override fun putRacingListData(list: List<Racing>) {

    }

    override fun putUserData(rowerUser: RowerUser) {

    }

    override fun putTrainingData(training: Training) {

    }

    override fun getLastTrainings(): List<Training> {

    }

    override fun getUserData(id: String): RowerUser {

    }

    override fun getRacingListData(list: List<Racing>): List<Racing> {

    }







}