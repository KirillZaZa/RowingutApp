package ru.kirilldev.rowingutapp.data.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.Training


@Dao
abstract class TrainingDao {


    @Insert(entity = Training::class, onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTraining(training: Training)

    @Query("SELECT * FROM training_table WHERE training_date LIKE :trainingDate")
    abstract suspend fun getTraining(trainingDate: String): Training

    @Query("SELECT * FROM training_table")
    abstract suspend fun getLastListTraining(): List<Training>

    @Update
    abstract suspend fun updateTraining(training: Training)

    @Delete(entity = Training::class)
    abstract suspend fun deleteTraining(trainingDate: String)

}