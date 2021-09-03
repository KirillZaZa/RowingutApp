package ru.kirilldev.rowingutapp.viewmodels.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>(initstate: T, private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    companion object{
        private const val SAVED_STATE_KEY: String = "savedstatekey"
    }

}

