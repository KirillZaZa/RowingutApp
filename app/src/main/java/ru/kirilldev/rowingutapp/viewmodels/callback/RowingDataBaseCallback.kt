package ru.kirilldev.rowingutapp.viewmodels.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class RowingDataBaseCallback : RoomDatabase.Callback() {

    companion object{
        private const val ROOM_CALLBACK_TAG = "room_callback_tag"
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)



    }


}