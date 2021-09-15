package ru.kirilldev.rowingutapp.extensions

import ru.kirilldev.rowingutapp.data.local.RowerUser
import ru.kirilldev.rowingutapp.viewmodels.ProfileData

fun RowerUser.toProfileData(): ProfileData {
    return ProfileData(
        name = this.name,
        email = this.email,
        img = this.img,
        age = this.age,
        weight = this.weight,
        height = this.height,
        averageBpm = this.averageBpm,
        averageCalories = this.averageCalories
    )
}