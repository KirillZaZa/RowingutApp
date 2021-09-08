package ru.kirilldev.rowingutapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getDate(): String {
        val dateFormat = SimpleDateFormat("dd/M/yyyy", Locale.ROOT)

        return dateFormat.format(Date())
    }
}