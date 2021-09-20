package ru.kirilldev.rowingutapp.viewmodels.interfaces

import android.net.Uri

interface IProfileViewModel {

    fun handleStatistic(chosenItem: Int)

    fun handleUpdateName(updatedName: String)

    fun handleUpdateWeight(updatedWeight: Float)

    fun handleUpdateHeight(updatedHeight: Float)

    fun handleUpdateAge(updatedAge: Int)

    fun handleUpdatePhoto(uri: Uri)

}