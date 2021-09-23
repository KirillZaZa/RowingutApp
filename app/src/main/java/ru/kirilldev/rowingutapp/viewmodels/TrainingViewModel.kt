package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.data.repository.RowingutRepository
import ru.kirilldev.rowingutapp.utils.DateUtil
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.ITrainingViewModel

class TrainingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<Training>(Training(), savedStateHandle), ITrainingViewModel {

    private val repository = RowingutRepository()

    init {
        subscribeOnDataSource(getTraining()) { training, state ->
            training ?: return@subscribeOnDataSource null
            state.copy(
                trainingDate = training.trainingDate,
                trainingName = training.trainingName,
                trainingTime = training.trainingTime,
                trainingTasks = training.trainingTasks,
                trainingType = training.trainingType
            )
        }

        subscribeOnDataSource(getSnackbarData()){snackbar, state->
            snackbar ?: return@subscribeOnDataSource null
            state.copy(
                isStarted = state.isStarted,
                currentTime = state.currentTime
            )
        }
    }

    // need to delegate this to datastore or shared prefs
    private fun getSnackbarData(): LiveData<TrainingSnackBarData?> {
        return MutableLiveData<TrainingSnackBarData>().apply {
            value = TrainingSnackBarData()
        }
    }

    private fun getTraining(): LiveData<Training?> {
        return repository.loadTraining(DateUtil.getDate())
    }

    override fun handleStartTraining(training: Training) {
       updateState {
           it.copy(isStarted = true)
       }
    }

    override fun handleStopTraining(currentTraining: Training) {
        updateState { it.copy(isStarted = false) }
    }


    override fun handleDeleteTraining(training: Training) {
        repository.deleteTraining(training.trainingDate!!) { isSucceeded->
            if(isSucceeded){
                resetState()
            }
        }
    }

    override fun handleTrainingChange(newTraining: Training) {
        repository.updateTraining(newTraining)
        updateState {
            it.copy(
                trainingDate = newTraining.trainingDate,
                trainingName = newTraining.trainingName,
                trainingType = newTraining.trainingType,
                trainingTasks = newTraining.trainingTasks,
                trainingTime = newTraining.trainingTime
            )
        }
    }


}

data class TrainingSnackBarData(
    var isOpen: Boolean = false,
    var currentTime: String? = null
)


class TrainingViewModelFactory(owner: SavedStateRegistryOwner) :
    AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(TrainingViewModel::class.java)) {
            return TrainingViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}