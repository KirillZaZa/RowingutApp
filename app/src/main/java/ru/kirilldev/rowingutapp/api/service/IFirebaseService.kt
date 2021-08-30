package ru.kirilldev.rowingutapp.api.service

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training

interface IFirebaseService {

    //define calls to firebase for getting the data

    @GET("/documents/racings")
    suspend fun getRacingsList(): Deferred<List<Racing>>

    @GET("/documents/todaytraining")
    suspend fun getTodayTraining(): Deferred<Training>

    @GET("/documents/users")
    suspend fun getRowerUserList(): Deferred<List<RowerUser>>

    @GET("/documents/users/{id}")
    suspend fun getRowerUser(@Query("id")id: String): Deferred<List<RowerUser>>

    @PUT("/documents/users")
    suspend fun updateRowerUser(id: String)

    @PUT("/documents/todayTraining/{date}")
    suspend fun updateTodayTraining(@Query("date")date: String)



}