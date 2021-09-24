package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.EntrySettings
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IOnBoardingViewModel
import java.lang.IllegalArgumentException

class OnBoardingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<ActivityOnBoardingData>(
        ActivityOnBoardingData(),
        savedStateHandle
    ), IOnBoardingViewModel {


    init {
        subscribeOnDataSource(getEntryInfo()) { entry, state ->
            state.copy(
                isFirstEntry = entry.isFirstEntry,
                isSignedIn = entry.isSignedIn
            )
        }
    }

    private val repository = RowingutRepository()

    private fun getEntryInfo(): LiveData<ActivityOnBoardingData> {
        val settings = repository.getEntrySettings().value
        return MutableLiveData<ActivityOnBoardingData>().apply {
            value = ActivityOnBoardingData(
                isSignedIn = settings!!.isSignedIn,
                isFirstEntry = settings.isFirstEntry
            )
        }
    }

    override fun handleCurrentPage(page: Int) {
        updateState { it.copy(currentPage = page) }
    }

    override fun handleEntrySettings(entrySettings: EntrySettings) {
        repository.updateEntrySettings(entrySettings)
        updateState {
            it.copy(
                isFirstEntry = entrySettings.isFirstEntry,
                isSignedIn = entrySettings.isSignedIn
            )
        }
    }
}

data class ActivityOnBoardingData(
    val currentPage: Int = 0,
    val isSignedIn: Boolean = false,
    val isFirstEntry: Boolean = false
)


class OnBoardingViewModelFactory(
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)) {
            return OnBoardingViewModel(handle) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}