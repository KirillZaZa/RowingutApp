package ru.kirilldev.rowingutapp.viewmodels.interfaces


interface IRegistrationViewModel {

    fun handleSignIn(email: String, password: String)

    fun handleSignUp(email: String, password: String)

    fun handlePage(position: Int)

    fun handleIsPasswordsEquals(password: String, confirmedPassword: String)


}