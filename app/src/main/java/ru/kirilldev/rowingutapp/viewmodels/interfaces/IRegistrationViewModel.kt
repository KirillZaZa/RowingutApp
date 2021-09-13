package ru.kirilldev.rowingutapp.viewmodels.interfaces

interface IRegistrationViewModel {

    fun handleSignIn(callback: (Boolean)-> Unit)

    fun handleSignUp()

}