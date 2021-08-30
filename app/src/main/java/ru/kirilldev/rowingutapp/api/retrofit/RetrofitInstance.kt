package ru.kirilldev.rowingutapp.api.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kirilldev.rowingutapp.api.config.AppConfig
import ru.kirilldev.rowingutapp.api.service.IFirebaseService
import java.util.concurrent.TimeUnit

// initialize okhttp and retrofit

object RetrofitInstance{

    private val client by lazy{
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AppConfig.FIREBASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api by lazy{
        retrofit.create(IFirebaseService::class.java)
    }

}