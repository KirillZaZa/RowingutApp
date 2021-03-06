package ru.kirilldev.rowingutapp.api.service

import kotlinx.coroutines.Deferred
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.remote.RowerRank

interface IFirebaseService {

    //define calls to firebase for getting the data

    @GET("/documents/racings")
    suspend fun getRacingsList(): Deferred<List<Racing>>

    @GET("/documents/todaytraining")
    suspend fun getTodayTraining(): Deferred<Training>

    @GET("/documents/rowerranks")
    suspend fun getRowerRankList(): Deferred<List<RowerRank>>

    @GET("/documents/users/{id}")
    suspend fun getRowerUser(@Query("id")id: String): Deferred<RowerUser>

    @PUT("/documents/users")
    suspend fun updateRowerUser(newUser: RowerUser)

    @PUT("/documents/users")
    suspend fun putRowerUser(user: RowerUser)

    @PUT("documents/trainings")
    suspend fun putTraining(training: Training)

    @PUT("/documents/todayTraining/{date}")
    suspend fun updateTodayTraining(@Query("date")date: String)


    @DELETE("documents/todayTraining/{date}")
    suspend fun deleteTodayTraining(@Query("date")date: String)





}


