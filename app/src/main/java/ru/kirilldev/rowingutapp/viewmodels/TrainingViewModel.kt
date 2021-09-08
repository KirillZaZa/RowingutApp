package ru.kirilldev.rowingutapp.viewmodels

import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.repository.RowingutRepository
import ru.kirilldev.rowingutapp.utils.DateUtil
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel
import ru.kirilldev.rowingutapp.viewmodels.interfaces.ITrainingViewModel

class TrainingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<Training, TrainingViewEvent>(Training(), savedStateHandle), ITrainingViewModel {

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
    }


    private fun getTraining(): LiveData<Training?> {
        return repository.loadTraining(DateUtil.getDate())
    }

    override fun handleStartTraining(training: Training) {
        handleEvent(TrainingViewEvent.StartTraining())
    }

    override fun handleStopTraining(currentTraining: Training) {
        handleEvent(TrainingViewEvent.StopTraining())
    }

    override fun handleAddTraining() {
        handleEvent(TrainingViewEvent.AddTraining())
    }

    override fun handleOpenTraining() {
        handleEvent(TrainingViewEvent.OpenTraining())
    }

    override fun handleLastTraining(training: Training) {
        handleEvent(TrainingViewEvent.OpenLastTraining(training))
    }

    override fun handleDeleteTraining(training: Training) {
        repository.deleteTraining(training.trainingDate!!){
            if(it) handleEvent(TrainingViewEvent.DeleteTraining())
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
        handleEvent(TrainingViewEvent.ChangeTraining())
    }


}


open class TrainingViewEvent {

    class OpenTraining : TrainingViewEvent()

    class OpenLastTraining(val training: Training) : TrainingViewEvent()

    class AddTraining : TrainingViewEvent()

    class StartTraining : TrainingViewEvent()

    class StopTraining : TrainingViewEvent()

    class DeleteTraining : TrainingViewEvent()

    class ChangeTraining : TrainingViewEvent()

}


class ViewModelFactory(owner: SavedStateRegistryOwner) :
    AbstractSavedStateViewModelFactory(owner, bundleOf()) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if(modelClass.isAssignableFrom(TrainingViewModel::class.java)){
            return TrainingViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}