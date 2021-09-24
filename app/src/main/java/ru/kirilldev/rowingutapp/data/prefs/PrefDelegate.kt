package ru.kirilldev.rowingutapp.data.prefs

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(
    private val defaultValue: T,
    private val customKey: String? = null,

){


    operator fun provideDelegate(
        thisRef: PrefManager,
        prop: KProperty<*>
    ): ReadWriteProperty<PrefManager, T>{
        val key = createKey(customKey ?: prop.name, defaultValue)
        return object : ReadWriteProperty<PrefManager, T>{

            private var storedValue: T? = null


            override fun getValue(thisRef: PrefManager, property: KProperty<*>): T {
                if(storedValue == null){
                    val flowValue = thisRef.dataStore.data
                        .map { prefs->
                            prefs[key] ?: defaultValue
                        }
                    storedValue = runBlocking(Dispatchers.IO) {
                        flowValue.first()
                    }
                }

                return storedValue!!
            }

            override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T) {
                storedValue = value
                thisRef.scope.launch{
                    thisRef.dataStore.edit { prefs->
                        prefs[key] = value
                    }
                }
            }

        }
    }

    @Suppress("UNCHECKED CAST")
    fun createKey(name: String, value: T): Preferences.Key<T> =
        when(value){
            is Boolean -> booleanPreferencesKey(name)
            else -> error("This type can't be stored into Preferences")
        }.run { this as Preferences.Key<T> }

}