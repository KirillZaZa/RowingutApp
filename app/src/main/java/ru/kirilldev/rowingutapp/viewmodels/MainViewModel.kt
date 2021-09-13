package ru.kirilldev.rowingutapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IMainViewModel

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