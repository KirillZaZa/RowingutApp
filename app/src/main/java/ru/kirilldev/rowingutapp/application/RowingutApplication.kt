package ru.kirilldev.rowingutapp.application

import android.app.Application
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.kirilldev.rowingutapp.data.room.db.RowingDatabase

class RowingutApplication : Application() {

    companion object{
        private var instance: RowingutApplication? = null
        var scope: CoroutineScope? = null
        var database: RowingDatabase? = null

        fun applicationContext(): Context = instance!!.applicationContext

//        val scope by lazy{
//            CoroutineScope(Dispatchers.Main + SupervisorJob())
//        }
//
//        val database by lazy{
//            RowingDatabase.getDataBase(instance!!.applicationContext, scope)
//        }
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
        scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        database = RowingDatabase.getDataBase(instance!!.applicationContext, scope!!)
    }



}