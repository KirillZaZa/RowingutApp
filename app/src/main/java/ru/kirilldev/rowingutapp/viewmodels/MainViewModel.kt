package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IMainViewModel
import java.lang.IllegalArgumentException

class MainViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<MainActivityData>(
        MainActivityData(),
        savedStateHandle
    ), IMainViewModel {


    override fun handleCurrentPage(page: Int) {
        updateState { it.copy(currentPage = page) }
    }


}

data class MainActivityData(
    val currentPage: Int = 0
)

class MainViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(handle) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}