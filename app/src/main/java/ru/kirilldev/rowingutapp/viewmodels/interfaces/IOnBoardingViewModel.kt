package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.EntrySettings

interface IOnBoardingViewModel {

    fun handleCurrentPage(page: Int)

    fun handleEntrySettings(entrySettings: EntrySettings)

}