package ru.kirilldev.rowingutapp.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.room.dao.RacingDao
import ru.kirilldev.rowingutapp.data.room.dao.RowerUserDao
import ru.kirilldev.rowingutapp.data.room.dao.TrainingDao
import ru.kirilldev.rowingutapp.data.room.callback.RowingDataBaseCallback
import ru.kirilldev.rowingutapp.data.room.exception.DatabaseNotExistsException


@Database(
    entities = [Racing::class, Training::class, RowerUser::class],
    version = 1,
    exportSchema = true
)
abstract class RowingDatabase : RoomDatabase() {

    abstract fun getRacingDao(): RacingDao
    abstract fun getTrainingDao(): TrainingDao
    abstract fun getRowerUserDao(): RowerUserDao

    companion object {

        @Volatile
        private var INSTANCE: RowingDatabase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): RowingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RowingDatabase::class.java,
                    "rowing_database"
                )
                    .addCallback(RowingDataBaseCallback(INSTANCE, scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }


        fun deleteDatabase() {
           if(INSTANCE == null){
               throw DatabaseNotExistsException()
           }
            INSTANCE!!.clearAllTables()
        }

    }

}