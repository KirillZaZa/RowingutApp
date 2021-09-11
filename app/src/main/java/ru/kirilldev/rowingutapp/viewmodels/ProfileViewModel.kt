package ru.kirilldev.rowingutapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel

class ProfileViewModel(savedStateHandle: SavedStateHandle):
    BaseViewModel<RowerUser>(RowerUser(), savedStateHandle) {

    private val repository = RowingutRepository()

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
