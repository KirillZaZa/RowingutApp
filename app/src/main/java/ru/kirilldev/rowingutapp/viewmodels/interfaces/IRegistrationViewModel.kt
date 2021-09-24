package ru.kirilldev.rowingutapp.viewmodels.interfaces

import ru.kirilldev.rowingutapp.data.local.EntrySettings


interface IRegistrationViewModel {

    fun handleSignIn(email: String, password: String)

    fun handleSignUp(email: String, password: String)

    fun handlePage(position: Int)

    fun handleIsPasswordsEquals(password: String, confirmedPassword: String)

    fun handleUpdateEntrySettings(entrySettings: EntrySettings)


}