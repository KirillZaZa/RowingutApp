package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.ICreateTrainingViewModel
import java.lang.IllegalArgumentException

class CreateTrainingViewModel(savedStateHandle: SavedStateHandle)
    : BaseViewModel<CreateTrainingData>(
    CreateTrainingData(), savedStateHandle
    ), ICreateTrainingViewModel{


    override fun handleType(index: Int) {
        updateState { it.copy(type = index) }
    }

    override fun handleName(newName: String) {
        updateState { it.copy(name = newName) }
    }

    override fun handleTime(newTime: Double) {
        updateState { it.copy(time = newTime) }
    }

    override fun handleTasks(newTasks: List<String>) {
        updateState { it.copy(tasks = newTasks) }
    }


}

data class CreateTrainingData(
    val type: Int? = 0,
    val name: String? = null,
    val time: Double? = null,
    val tasks: List<String>? = null
)

class CreateTrainingViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(CreateTrainingViewModel::class.java)){
            return CreateTrainingViewModel(handle) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}