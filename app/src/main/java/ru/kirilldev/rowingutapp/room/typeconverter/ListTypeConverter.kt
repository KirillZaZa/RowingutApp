package ru.kirilldev.rowingutapp.room.typeconverter

import androidx.room.TypeConverter


class ListTypeConverter {

    @TypeConverter
    fun fromList(list: List<String>) = list.joinToString(",")

    @TypeConverter
    fun toList(str: String) = listOf(str)

}