package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import java.lang.IllegalArgumentException

class SplashViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<EntrySettings>(EntrySettings(), savedStateHandle) {

    init {
        subscribeOnDataSource(getEntryInfo()){ entry, state->
            entry ?: return@subscribeOnDataSource null
            state.copy(

            )
        }
    }

    private val repository = RowingutRepository()

    private fun getEntryInfo(): LiveData<EntrySettings>{

    }

}


class SplashViewModelFactory(
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java))
            return SplashViewModel(handle) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}