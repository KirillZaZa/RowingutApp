package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IOnBoardingViewModel
import java.lang.IllegalArgumentException

class OnBoardingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<ActivityOnBoardingData>(
        ActivityOnBoardingData(),
        savedStateHandle
    ), IOnBoardingViewModel {

    override fun handleCurrentPage(page: Int) {
        updateState { it.copy(currentPage = page) }
    }
}

data class ActivityOnBoardingData(
    val currentPage: Int = 0
)


class OnBoardingViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(OnBoardingViewModel::class.java)){
            return OnBoardingViewModel(handle) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}