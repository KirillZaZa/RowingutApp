package ru.kirilldev.rowingutapp.viewmodels.interfaces

interface ICreateTrainingViewModel {

    fun handleType(index: Int)

    fun handleName(newName: String)

    fun handleTime(newTime: Double)

    fun handleTasks(newTasks: List<String>)

}