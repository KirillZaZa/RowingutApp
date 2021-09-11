package ru.kirilldev.rowingutapp.room.dao

import androidx.room.*
import ru.kirilldev.rowingutapp.data.local.RowerUser


@Dao
abstract class RowerUserDao {


    @Insert(entity = RowerUser::class, onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insertRowerUsers(rowerUser: RowerUser)

    @Query("SELECT * FROM rower_table")
    abstract suspend fun getRowerUser(): RowerUser

    @Query("SELECT * FROM rower_table")
    abstract suspend fun getRowerList(): List<RowerUser>

    @Update(entity = RowerUser::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateRowerUser(user: RowerUser)

    @Delete(entity = RowerUser::class)
    abstract suspend fun deleteRowerUser(rowerUser: RowerUser)

}