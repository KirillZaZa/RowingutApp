package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.Racing


@Dao
interface RacingDao {

    @Insert(entity = Racing::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRacing(racing: Racing)

    @Query("SELECT * FROM racing_table")
    suspend fun getRacingList(): List<Racing>

    @Query("SELECT * FROM racing_table WHERE racing_date LIKE :date")
    suspend fun getRacing(date: String): Racing

    @Update(entity = Racing::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRacing(date: String)

    @Delete(entity = Racing::class)
    suspend fun deleteRacing(racing: Racing)

}