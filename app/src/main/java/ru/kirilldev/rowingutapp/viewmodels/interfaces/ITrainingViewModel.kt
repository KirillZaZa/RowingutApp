package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.Training

interface ITrainingViewModel {

    fun handleStartTraining(training: Training)

    fun handleStopTraining(currentTraining: Training)

    fun handleDeleteTraining(training: Training)

    fun handleTrainingChange(newTraining: Training)


}