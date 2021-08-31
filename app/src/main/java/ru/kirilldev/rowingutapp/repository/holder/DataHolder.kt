package ru.kirilldev.rowingutapp.repository.holder

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kirilldev.rowingutapp.api.retrofit.RetrofitInstance
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import java.lang.Exception

object FirebaseDataHolder {


    /**
     *
     * @FirebaseDataHolder is intended for network interaction
     *
     */


    private val scope = RowingutApplication.scope

    /*
    TODO: Need to finish the realisation of network and local contribution with data
     */

    /**
     * getting training of the day
     *
     */
    fun getTodayTraining(): MutableLiveData<Training?> {
        val trainingLiveData = MutableLiveData<Training?>()
        try {
            scope.launch {
                var response: Training?
                withContext(Dispatchers.IO) {
                    response = RetrofitInstance.api.getTodayTraining().await()
                }
                trainingLiveData.value = response
            }
        } catch (e: Exception) {
            Log.e("LoadTodayTraining", "${e.message}")
            trainingLiveData.value = null
        }
        return trainingLiveData
    }


    /**
     *
     *
     */
    fun getRacingsList(): MutableLiveData<List<Racing>?>{
        val racingsLiveData = MutableLiveData<List<Racing>?>()
        try {
            scope.launch {
                var response: List<Racing>?
                withContext(Dispatchers.IO){
                    response = RetrofitInstance.api.getRacingsList().await()
                }
                racingsLiveData.value = response
            }
        }catch (e: Exception){
            Log.e("getRacingsList", "${e.message}")
            racingsLiveData.value = null
        }

        return racingsLiveData
    }

    fun getRowerUserList(): MutableLiveData<List<RowerUser>?>{
        val userList = MutableLiveData<List<RowerUser>?>()

        //TODO

        return userList
    }



}


object LocalDataHolder {


    /**
     * @LocalDataHolder is intended for local interaction
     *
     * Here is the interaction with the database Room
     *
     */
}