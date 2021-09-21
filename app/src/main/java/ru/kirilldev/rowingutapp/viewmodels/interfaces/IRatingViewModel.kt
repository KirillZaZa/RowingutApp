package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.RowerUser

interface IRatingViewModel {


    fun handleUpdateListRank(rowerUser: RowerUser)

    fun handleLoadImages(position: Int)

}