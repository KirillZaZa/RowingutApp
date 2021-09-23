package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.firebase.storage.ImageStorage
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.data.remote.RowerRank
import ru.kirilldev.rowingutapp.extensions.getSortedRowerRankList
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IRatingViewModel
import java.lang.IllegalArgumentException

class RatingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<RowerRank>(
        savedStateHandle = savedStateHandle,
    ), IRatingViewModel {

    private val repository = RowingutRepository()
    private val imageStorage = ImageStorage()

    init {
        subscribeOnListDataSource(getRatingList())
    }

    private fun getRatingList() =
        repository.loadRowerRankList().value?.getSortedRowerRankList() ?: emptyList()



    override fun handleUpdateListRank(rowerUser: RowerUser) {
        repository.updateRowerUser(user = rowerUser){ isSuccessfully->
            if(isSuccessfully){

                updateListState {
                    getRatingList().getSortedRowerRankList()
                }

            }else updateListState { emptyList() }
        }
    }

    override fun handleLoadImages(position: Int) {
        currentListState.forEach { rower->
            imageStorage.downloadImage(rower.img){ image->
                rower.imageBitmap = image
            }
        }
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
