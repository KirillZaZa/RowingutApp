package ru.kirilldev.rowingutapp.viewmodels

import android.net.Uri
import androidx.core.os.bundleOf
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.firebase.storage.ImageStorage
import ru.kirilldev.rowingutapp.data.firebase.storage.Status
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.base.Notify
import ru.kirilldev.rowingutapp.viewmodels.interfaces.IProfileViewModel
import java.lang.IllegalArgumentException

class ProfileViewModel(savedStateHandle: SavedStateHandle):
    BaseViewModel<ProfileData>(ProfileData(), savedStateHandle), IProfileViewModel{

    private val repository = RowingutRepository()
    private val imageStorage  = ImageStorage()



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
                statisticPeriod = state.statisticPeriod
            )
        }
    }

    private fun getRowerData() = repository.loadUserData()


    override fun handleStatistic(chosenItem: Int) {
        updateState {
            it.copy(
                statisticPeriod = chosenItem
            )
        }
    }

    override fun handleUpdateName(updatedName: String) {
        updateState { it.copy(name = updatedName)  }
    }

    override fun handleUpdateWeight(updatedWeight: Float) {
        updateState { it.copy(weight = updatedWeight) }
    }

    override fun handleUpdateHeight(updatedHeight: Float) {
        updateState { it.copy(height = updatedHeight) }
    }

    override fun handleUpdateAge(updatedAge: Int) {
        updateState { it.copy(age = updatedAge) }
    }

    override fun handleUpdatePhoto(uri: Uri) {
        imageStorage.uploadImage(uri){
            if (it == Status.SUCCESS.name){
                notify(Notify.Success(it))
            } else notify(Notify.Error(it))
        }
    }


}

data class ProfileData(
    val name: String? = null,
    val email: String? = null,
    val img: String? = null,
    val age: Int? = 0,
    val weight: Float? = 0f,
    val height: Float? = 0f,
    val averageBpm: Int = 0,
    val averageCalories: Int = 0,
    val statisticPeriod: Int = 0

){
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other == null || other?.javaClass != javaClass) return false

        other as ProfileData
        if(name != other.name) return false
        if(email != other.email) return false
        if(img != other.img) return false
        if(age != other.age) return false
        if(weight != other.weight) return false
        if(height != other.height) return false
        if(averageBpm != other.averageBpm) return false
        if(averageCalories != other.averageCalories) return false
        if(statisticPeriod != other.statisticPeriod) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (img?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        result = 31 * result + (weight?.hashCode() ?: 0)
        result = 31 * result + (height?.hashCode() ?: 0)
        result = 31 * result + averageBpm
        result = 31 * result + averageCalories
        result = 31 * result + statisticPeriod
        return result
    }
}

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
