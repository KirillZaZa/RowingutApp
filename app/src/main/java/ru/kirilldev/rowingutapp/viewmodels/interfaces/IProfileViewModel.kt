package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.RowerUser

interface IProfileViewModel {

    fun handleStatistic(chosenItem: Int)

    fun handleUpdateName(updatedName: String)

    fun handleUpdateWeight(updatedWeight: Float)

    fun handleUpdateHeight(updatedHeight: Float)

    fun handleUpdateAge(updatedAge: Int)

}