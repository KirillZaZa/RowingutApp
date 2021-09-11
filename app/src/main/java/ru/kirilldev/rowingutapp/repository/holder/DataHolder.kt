package ru.kirilldev.rowingutapp.repository.holder

import android.util.Log
import androidx.core.os.trace
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.api.retrofit.RetrofitInstance
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.remote.RowerRank
import ru.kirilldev.rowingutapp.extensions.printError
import ru.kirilldev.rowingutapp.repository.interfaces.ILocalHolder
import java.lang.Exception
import java.util.*

object FirebaseDataHolder {


    /**
     *
     * @FirebaseDataHolder is intended for network interaction
     *
     */

    private const val FIREBASE_TAG = "firebase_tag"
    private val scope = RowingutApplication.scope!!
    private val api = RetrofitInstance.api

    /*
    TODO: Need to finish the realisation of network and local contribution with data
     */

    /**
     * getting training of the day
     *
     */

    private fun Job.cancelingJob() {
        if (this.isCompleted) this.cancel()
    }

    fun getTodayTraining(callback: (LiveData<Training?>) -> Unit) {
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
        callback(trainingLiveData)
        trainingJob.cancelingJob()
    }

    fun deleteTraining(date: String,isSuccessfully: (Boolean) -> Unit){

    }


    /**
     *
     *
     */
    fun getRacingsList(): LiveData<List<Racing>?> {
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

        racingJob.cancelingJob()

        return racingsLiveData
    }

    fun getRowerRankList(): LiveData<List<RowerRank>?> {
        val rowerRankList = MutableLiveData<List<RowerRank>?>()

        val rowerListJob = scope.launch {
            try {
                val response: List<RowerRank>?

                withContext(Dispatchers.IO) {
                    response = api.getRowerRankList().await()
                }
                rowerRankList.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                rowerRankList.value = null
            }
        }

        rowerListJob.cancelingJob()


        return rowerRankList
    }

    fun putRowerUser(user: RowerUser) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.putRowerUser(user)
                }
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
            }
        }

        job.cancelingJob()
    }

    fun getRowerUser(id: String): LiveData<RowerUser?> {
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

        userJob.cancelingJob()

        return rowerUser
    }

    fun updateRowerUser(newUser: RowerUser) {
        val updateRowerJob = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.updateRowerUser(newUser)
                }
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
            }
        }

        updateRowerJob.cancelingJob()
    }

    fun updateTodayTraining(newTraining: Training, isSuccessfully: (Boolean) -> Unit) {
        var success = true
        val updateTrainingJob = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.updateTodayTraining(newTraining.trainingDate!!)
                }
            } catch (e: CancellationException) {
                e.printError(FIREBASE_TAG)
                success = false
            }
        }
        updateTrainingJob.cancelingJob()
        isSuccessfully(success)

    }


    fun putTraining(training: Training, isSuccessfully: (Boolean) -> Unit){
        var success = true
        val job = scope.launch {
            try{
                withContext(Dispatchers.IO){
                    api.putTraining(training)
                }
            }catch (e :Exception){
                e.printError(FIREBASE_TAG)
                success = false
                this.cancel()
            }
        }
        job.cancelingJob()
        isSuccessfully(success)
    }



}


object LocalDataHolder : ILocalHolder {

    /**
     * @LocalDataHolder is intended for interaction with local storage
     *
     * Here is the interaction with the database Room
     *
     */
    private const val LOCAL_STORAGE_TAG = "local_storage_tag"


    private val scope = RowingutApplication.scope!!
    private val dataBase = RowingutApplication.database!!


    private fun Job.cancellingJob() {
        if (this.isCompleted) this.cancel()
    }

    /**
     * USER METHODS
     */

    override fun getUserData(email: String): LiveData<RowerUser?> {
        val userData = MutableLiveData<RowerUser?>()
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    userData.value = dataBase.getRowerUserDao().getRowerUser()
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
                userData.value = null
            }
        }
        job.cancellingJob()

        return userData
    }

    override fun putUserData(rowerUser: RowerUser) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRowerUserDao().insertRowerUsers(rowerUser)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }

    override fun getRowerList(): LiveData<List<RowerUser>?> {
        val rowerList = MutableLiveData<List<RowerUser>?>()

        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    rowerList.value = dataBase.getRowerUserDao().getRowerList()
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
                rowerList.value = null
            }
        }
        job.cancellingJob()
        return rowerList
    }

    override fun updateRowerUser(user: RowerUser) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRowerUserDao().updateRowerUser(user)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }

    override fun deleteRowerUser(user: RowerUser) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRowerUserDao().deleteRowerUser(user)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }


    /**
     * TRAINING METHODS
     */

    override fun putTrainingData(training: Training) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getTrainingDao().insertTraining(training)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }

    override fun getLastTrainings(): LiveData<List<Training>?> {
        val trainingListData = MutableLiveData<List<Training>?>()
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    trainingListData.value = dataBase.getTrainingDao().getLastListTraining()
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()

        return trainingListData
    }

    override fun getTraining(date: String): LiveData<Training?> {
        val trainingData = MutableLiveData<Training?>()
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    trainingData.value = dataBase.getTrainingDao().getTraining(date)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
                trainingData.value = null
            }
        }
        job.cancellingJob()

        return trainingData
    }

    override fun updateTraining(training: Training) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getTrainingDao().updateTraining(training)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }

    override fun deleteTraining(date: String) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getTrainingDao().deleteTraining(date)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }


    /**
     * RACING METHODS
     */

    override fun putRacingListData(racing: Racing) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRacingDao().insertRacing(racing)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }
        job.cancellingJob()
    }

    override fun getRacingListData(): LiveData<List<Racing>?> {
        val racingListData = MutableLiveData<List<Racing>?>()
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    racingListData.value = dataBase.getRacingDao().getRacingList()
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
                racingListData.value = null
            }
        }
        job.cancellingJob()

        return racingListData
    }

    override fun getRacing(date: String): LiveData<Racing?> {
        val racingData = MutableLiveData<Racing?>()
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    racingData.value = dataBase.getRacingDao().getRacing(date)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
                racingData.value = null
            }
        }
        job.cancellingJob()

        return racingData
    }

    override fun updateRacing(racing: Racing) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRacingDao().updateRacing(racing)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }

        job.cancellingJob()
    }

    override fun deleteRacing(racing: Racing) {
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dataBase.getRacingDao().deleteRacing(racing)
                }
            } catch (e: CancellationException) {
                e.printError(LOCAL_STORAGE_TAG)
            }
        }

        job.cancellingJob()
    }


}