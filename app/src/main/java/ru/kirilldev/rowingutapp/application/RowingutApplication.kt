package ru.kirilldev.rowingutapp.application

import android.app.Application
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.kirilldev.rowingutapp.room.db.RowingDatabase

class RowingutApplication : Application() {

    companion object{
        val instance by lazy{
            RowingutApplication()
        }

        val scope by lazy{
            CoroutineScope(Dispatchers.Main + SupervisorJob())
        }

        val database by lazy{
            RowingDatabase.getDataBase(context = instance.applicationContext, scope)
        }
    }




}