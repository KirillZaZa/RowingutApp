package ru.kirilldev.rowingutapp.data.prefs

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import ru.kirilldev.rowingutapp.application.RowingutApplication
import ru.kirilldev.rowingutapp.data.local.EntrySettings


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "entry_settings")

class PrefManager(val context: Context = RowingutApplication.applicationContext()) {

    val dataStore = context.dataStore

    private val errHandler = CoroutineExceptionHandler{_, th->
        Log.e("PrefManager", "err ${th.message}")
    }

    internal val scope = CoroutineScope(SupervisorJob() + errHandler)

    var isFirstEntry by PrefDelegate(false)

    var isSignedIn by PrefDelegate(false)

    val entrySettings: LiveData<EntrySettings>
        get() {
            val entry = dataStore.data.map { it[booleanPreferencesKey(this::isFirstEntry.name)] ?: false}
            val signedIn = dataStore.data.map { it[booleanPreferencesKey(this::isSignedIn.name)] ?: false }

            return entry.zip(signedIn){ en, signed ->
                EntrySettings(en, signed)}
                .onEach { Log.e("PrefManager", "settings: $it") }
                .distinctUntilChanged()
                .asLiveData()



        }



}