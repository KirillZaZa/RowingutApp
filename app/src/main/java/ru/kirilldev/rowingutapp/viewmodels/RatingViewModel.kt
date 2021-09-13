package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.remote.RowerRank
import ru.kirilldev.rowingutapp.extensions.getSortedRowerRankList
import ru.kirilldev.rowingutapp.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRatingViewModel
import java.lang.IllegalArgumentException

class RatingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<RowerRank>(
        savedStateHandle = savedStateHandle,
    ), IRatingViewModel {

    private val repository = RowingutRepository()

    init {
        subscribeOnListDataSource(getRatingList())
    }

    private fun getRatingList() = repository.loadRowerRankList().value



    override fun handleUpdateListRank(rowerUser: RowerUser, loading: (Boolean)-> Unit) {
        loading(true)

        repository.updateRowerUser(user = rowerUser){ isSuccessfully->
            if(isSuccessfully){

                updateListState {
                    getRatingList()?.getSortedRowerRankList() ?: emptyList()
                }

            }else updateListState { emptyList() }
        }

        loading(false)
    }


}


class RatingViewModelFactory(owner: SavedStateRegistryOwner)
    : AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(RatingViewModel::class.java)){
            return RatingViewModel(handle) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
