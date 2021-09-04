package ru.kirilldev.rowingutapp.application

import android.app.Application
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.kirilldev.rowingutapp.room.db.RowingDatabase

class RowingutApplication : Application() {

    companion object {
        var INSTANCE: RowingutApplication? = null
        var scope: CoroutineScope? = null
        var database: RowingDatabase? = null
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        database = RowingDatabase.getDataBase(context = INSTANCE!!.applicationContext, scope!!)
    }

}