package ru.kirilldev.rowingutapp.data.repository.holder

import android.util.Log
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
import ru.kirilldev.rowingutapp.data.repository.interfaces.ILocalHolder
import java.lang.Exception

object FirebaseDataHolder {
    /**
     *
     * @FirebaseDataHolder is intended for network interaction
     *
     */

    private const val FIREBASE_TAG = "firebase_tag"
    private val scope = RowingutApplication.scope
    private val api = RetrofitInstance.api


    private fun Job.cancelingJob() {
        if (this.isCompleted) this.cancel()
    }

    fun putRowerRank(rowerRank: RowerRank){
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO){
                    api.putRowerRank(rowerRank)
                }
            }catch (e: CancellationException){
                e.printError(FIREBASE_TAG)
            }
        }
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
        trainingJob.cancelingJob()
        callback(trainingLiveData)
    }

    fun deleteTraining(date: String, isSuccessfully: (Boolean) -> Unit) {
        var success = false
        val job = scope.launch {
            success = try {
                withContext(Dispatchers.IO) {
                    api.deleteTodayTraining(date)
                }
                true
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                false
            }
        }
        job.cancelingJob()
        isSuccessfully(success)

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
                e.printError(FIREBASE_TAG)
                rowerRankList.value = null
            }
        }

        rowerListJob.cancelingJob()


        return rowerRankList
    }

    fun putRowerUser(user: RowerUser, isSuccessfully: (Boolean) -> Unit) {
        var success = false
        val job = scope.launch {
            success = try {
                withContext(Dispatchers.IO) {
                    api.putRowerUser(user)
                }
                true
            } catch (e: CancellationException) {
                e.printError(FIREBASE_TAG)
                false
            }
        }
        job.cancelingJob()
        isSuccessfully(success)

    }

    fun getRowerUser(email: String, callback: (LiveData<RowerUser?>) -> Unit){
        val rowerUser = MutableLiveData<RowerUser?>()
        val userJob = scope.launch {
            try {
                val response: RowerUser
                withContext(Dispatchers.IO) {
                    response = api.getRowerUser(email).await()
                }
                rowerUser.value = response
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                rowerUser.value = null
            }
        }

        userJob.cancelingJob()

        callback(rowerUser)
    }

    fun updateRowerUser(newUser: RowerUser, isSuccessfully: (Boolean) -> Unit) {
        var success = false
        val updateRowerJob = scope.launch {
            success = try {
                withContext(Dispatchers.IO) {
                    api.updateRowerUser(newUser)
                }
                true
            } catch (e: CancellationException) {
                Log.e(FIREBASE_TAG, "${e.message}")
                false
            }
        }
        updateRowerJob.cancelingJob()
        isSuccessfully(success)
    }

    fun updateTodayTraining(newTraining: Training, isSuccessfully: (Boolean) -> Unit) {
        var success = false
        val updateTrainingJob = scope.launch {
            success = try {
                withContext(Dispatchers.IO) {
                    api.updateTodayTraining(newTraining.trainingDate!!)
                }
                true
            } catch (e: CancellationException) {
                e.printError(FIREBASE_TAG)
                false
            }
        }
        updateTrainingJob.cancelingJob()
        isSuccessfully(success)

    }


    fun putTraining(training: Training, isSuccessfully: (Boolean) -> Unit) {
        var success = true
        val job = scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    api.putTraining(training)
                }
            } catch (e: Exception) {
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


    private val scope = RowingutApplication.scope
    private val dataBase = RowingutApplication.database


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