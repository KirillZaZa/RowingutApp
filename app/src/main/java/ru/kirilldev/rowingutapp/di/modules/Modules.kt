//package ru.kirilldev.rowingutapp.di.modules
//
//import dagger.Module
//import dagger.Provides
//import ru.kirilldev.rowingutapp.data.local.RowerUser
//
//@Module
//object ApplicationModule {
//
//    @Provides
//    fun provideRacing(
//        id: String,
//        name: String,
//        hash: String,
//        email: String,
//        age: Int?,
//        weight: Int?,
//        height: Int?,
//        calories: Int,
//        averageBpm: Int,
//        ratingPlace: Int
//    ) = RowerUser(
//        id = id,
//        name = name,
//        hash = hash,
//        email = email,
//        age = age,
//        weight = weight,
//        height = height,
//        calories = calories,
//        averageBpm = averageBpm,
//        ratingPlace = ratingPlace
//    )
//}