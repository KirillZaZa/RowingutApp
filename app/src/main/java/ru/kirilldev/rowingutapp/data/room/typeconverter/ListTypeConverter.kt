package ru.kirilldev.rowingutapp.data.room.typeconverter

import androidx.room.TypeConverter


class ListTypeConverter {

    @TypeConverter
    fun fromList(list: List<String>) = list.joinToString(",")

    @TypeConverter
    fun toList(str: String) = listOf(str)

}