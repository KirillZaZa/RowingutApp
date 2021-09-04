package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.Racing


@Dao
abstract class RacingDao {

    @Insert(entity = Racing::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRacing(racing: Racing)

    @Query("SELECT * FROM racing_table")
    abstract suspend fun getRacingList(): List<Racing>

    @Query("SELECT * FROM racing_table WHERE racing_date LIKE :date")
    abstract suspend fun getRacing(date: String): Racing

    @Update(entity = Racing::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateRacing(racing: Racing)

    @Delete(entity = Racing::class)
    abstract suspend fun deleteRacing(racing: Racing)

}