package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.RowerUser


@Dao
interface RowerUserDao {


    @Insert(entity = RowerUser::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun insertRowerUsers(rowerUser: List<RowerUser>)

    @Query("SELECT * FROM rower_table WHERE rower_id LIKE :id")
    suspend fun getRowerUser(id: String): RowerUser

    @Query("SELECT * FROM rower_table")
    suspend fun getRowerList(): List<RowerUser>

    @Update(entity = RowerUser::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRowerUser(id: String)

    @Delete(entity = RowerUser::class)
    suspend fun deleteRowerUser(rowerUser: RowerUser)

}