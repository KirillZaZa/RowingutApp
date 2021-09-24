package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.Racing
import ru.kirilldev.rowingutapp.data.local.RacingInfo
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRacingViewModel
import java.lang.IllegalArgumentException

class RacingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<Racing>(Racing(), savedStateHandle), IRacingViewModel {

    private var repository = RowingutRepository()

    init {

        subscribeOnListDataSource(getRacingList())

        subscribeOnDataSource(getRacingListItem()) { item, state ->
            item ?: return@subscribeOnDataSource null
            state.copy(
                openedItemIndex = state.openedItemIndex,
                itemDate = state.itemDate,
                itemRacings = state.itemRacings
            )
        }


    }


    private fun getRacingList() = repository.loadRacingListData().value

    private fun getRacingListItem(): LiveData<RacingListItem?> {
        return MutableLiveData<RacingListItem>().apply {
            value = RacingListItem()
        }
    }

    override fun handleOpenRacing(racing: Racing, index: Int) {
        updateState { it.copy(
            openedItemIndex = index,
            itemDate = racing.racingDate,
        )}
    }

    override fun handleCloseRacing() {
        updateState { it.copy(
            openedItemIndex = null,
            itemDate = null,
            itemRacings = null
        ) }
    }

    override fun handleUpdateRacingList() {
        updateListState { getRacingList()!! }
    }

}



data class RacingListItem(
    val openedItemIndex: Int? = null,
    val itemDate: String? = null,
    val itemRacings: List<RacingInfo>? = null
)


class RacingViewModelFactory(owner: SavedStateRegistryOwner) :
    AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(RacingViewModel::class.java)) {
            return RacingViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}