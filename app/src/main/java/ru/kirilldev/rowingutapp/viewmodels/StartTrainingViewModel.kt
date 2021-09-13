package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IStartTrainingViewModel
import java.lang.IllegalArgumentException

class StartTrainingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<StartTrainingData>(
        StartTrainingData(),
        savedStateHandle
    ), IStartTrainingViewModel {

    override fun handleStart(hasBeenStarted: Boolean) {
       updateState { it.copy(isStarted = hasBeenStarted) }
    }

    override fun handleTime(time: String) {
        updateState { it.copy(currentTime = time) }
    }
}


data class StartTrainingData(
    val isStarted: Boolean = false,
    val currentTime: String? = null
)

class StartTrainingViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(StartTrainingViewModel::class.java))
            StartTrainingViewModel(handle) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}