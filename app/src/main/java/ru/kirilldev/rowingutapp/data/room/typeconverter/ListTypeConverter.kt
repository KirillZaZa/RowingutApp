package ru.kirilldev.rowingutapp.data.room.typeconverter

import androidx.room.TypeConverter


class ListTypeConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toList(str: String): List<String> {
        return listOf(str)
    }

}