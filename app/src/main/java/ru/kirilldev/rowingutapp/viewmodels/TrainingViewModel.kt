package ru.kirilldev.rowingutapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import ru.kirilldev.rowingutapp.data.local.Training
import ru.kirilldev.rowingutapp.viewmodels.base.BaseViewModel

class TrainingViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<Training>(Training(), savedStateHandle) {
}