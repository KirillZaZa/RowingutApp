package ru.kirilldev.rowingutapp.data.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ru.kirilldev.rowingutapp.application.RowingutApplication


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "entry_settings")

class PrefManager(val context: Context = RowingutApplication.instance.applicationContext) {

    val dataStore = context.dataStore


}