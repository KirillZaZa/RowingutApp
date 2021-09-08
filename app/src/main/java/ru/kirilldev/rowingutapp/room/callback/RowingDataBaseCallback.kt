package ru.kirilldev.rowingutapp.room.callback

import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RacingInfo
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.room.dao.RacingDao
import ru.kirilldev.rowingutapp.room.dao.RowerUserDao
import ru.kirilldev.rowingutapp.room.dao.TrainingDao
import ru.kirilldev.rowingutapp.room.db.RowingDatabase
import ru.kirilldev.rowingutapp.utils.DateUtil
import java.text.SimpleDateFormat
import java.util.*

class RowingDataBaseCallback(
    private val database: RowingDatabase?,
    private val scope: CoroutineScope = RowingutApplication.scope!!
) :
    RoomDatabase.Callback() {

    companion object {
        private const val ROOM_CALLBACK_TAG = "room_callback_tag"
    }

    private lateinit var userDao: RowerUserDao
    private lateinit var racingDao: RacingDao
    private lateinit var trainingDao: TrainingDao

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Log.e(ROOM_CALLBACK_TAG, "creating database...")


        val dbJob =  scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    rePopulateDb()
                }
                Log.e(ROOM_CALLBACK_TAG, "Data has been successfully loaded...")
            }catch (e: CancellationException){
                Log.e(ROOM_CALLBACK_TAG, "${e.message}")
            }
        }
        if(dbJob.isCompleted) dbJob.cancel()
    }

    private suspend fun rePopulateDb() {
        database?.let { db->
            userDao = db.getRowerUserDao()
            racingDao = db.getRacingDao()
            trainingDao = db.getTrainingDao()

            userDao.insertRowerUsers(rowerUser = createTestUser())
            racingDao.insertRacing(racing = createTestRacing())
            trainingDao.insertTraining(training = createTestTraining())
        }
    }


    private fun createTestUser() = RowerUser(
        id = UUID.randomUUID().toString(),
        name = "Вася",
        hash = "",
        email = "example@mail.com",
        img = "",
        age = 18,
        weight = 85.3f,
        height = 191.2f,
        calories = 450,
        averageBpm = 60,
        ratingPlace = 0
    )

    private fun createTestRacing() = Racing(
        racingDate = DateUtil.getDate(),
        racingPlace = "Стрела",
        racingListInfo = RacingInfo(1000, "Командная")
    )

    private fun createTestTraining() = Training(
        trainingDate = DateUtil.getDate(),
        trainingName = "Женя, хватит",
        trainingTime = 2.5f,
        trainingTasks = arrayListOf(
            "1.Отжимания 60",
            "2.Присед 30х40х5",
            "3.Становая тяга"
        ),
        trainingType = "Кардио"
    )




}