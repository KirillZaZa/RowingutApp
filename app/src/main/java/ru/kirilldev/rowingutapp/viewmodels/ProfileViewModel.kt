package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import java.lang.IllegalArgumentException

class ProfileViewModel(savedStateHandle: SavedStateHandle):
    BaseViewModel<RowerUser>(RowerUser(), savedStateHandle) {

    private val repository = RowingutRepository()

    /**
     * Закончить работу над моделью профиля
     *
     */

    init {
        subscribeOnDataSource(getRowerData()){rowerUser, state->
            rowerUser ?: return@subscribeOnDataSource null
            state.copy(
                name = rowerUser.name,
                email = rowerUser.email,
                img = rowerUser.img,
                age = rowerUser.age,
                weight = rowerUser.weight,
                height = rowerUser.height,
                averageCalories = rowerUser.averageCalories,
                averageBpm = rowerUser.averageBpm,
                statisticItem = state.statisticItem
            )
        }
    }

    private fun getRowerData() = repository.loadUserData()




}

data class ProfileData()

class ProfileViewModelFactory(
    owner: SavedStateRegistryOwner
): AbstractSavedStateViewModelFactory(owner, bundleOf()){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java))
            ProfileViewModel(handle)
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
