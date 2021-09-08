package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.Training


@Dao
abstract class TrainingDao {


    @Insert(entity = Training::class, onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insertTraining(training: Training)

    @Query("SELECT * FROM training_table WHERE training_date LIKE :date")
    abstract suspend fun getTraining(date: String): Training

    @Query("SELECT * FROM training_table")
    abstract suspend fun getLastListTraining(): List<Training>

    @Update(entity = Training::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateTraining(training: Training)

    @Delete(entity = Training::class)
    abstract suspend fun deleteTraining(date: String)

}