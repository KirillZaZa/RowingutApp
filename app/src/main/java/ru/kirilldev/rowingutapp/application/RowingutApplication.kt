package ru.kirilldev.rowingutapp.application

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class RowingutApplication: Application() {

    companion object{
        private lateinit var scope: CoroutineScope
    }

    override fun onCreate() {
        super.onCreate()
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    }

}