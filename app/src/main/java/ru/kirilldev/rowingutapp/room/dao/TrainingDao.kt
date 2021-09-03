package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.Training


@Dao
interface TrainingDao {


    @Insert(entity = Training::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTraining(training: Training)

    @Query("SELECT * FROM training_table WHERE training_date LIKE :date")
    suspend fun getTraining(date: String)

    @Query("SELECT * FROM training_table WHERE training_date != :date")
    suspend fun getLastListTraining(date: String): List<Training>

    @Update(entity = Training::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTraining(training: Training)

    @Delete(entity = Training::class)
    suspend fun deleteTraining(training: Training)

}