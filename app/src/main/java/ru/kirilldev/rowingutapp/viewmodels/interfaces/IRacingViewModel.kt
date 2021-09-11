package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.Racing

interface IRacingViewModel {

    fun handleOpenRacing(racing: Racing, index: Int)

    fun handleCloseRacing()

    fun handleUpdateRacingList()

}