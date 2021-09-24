package ru.kirilldev.rowingutapp.data.local

import java.io.Serializable


data class EntrySettings(
    val isFirstEntry: Boolean = false,
    val isSignedIn: Boolean = false
)