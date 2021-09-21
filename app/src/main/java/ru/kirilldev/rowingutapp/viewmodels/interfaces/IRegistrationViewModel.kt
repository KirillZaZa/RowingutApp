package ru.kirilldev.rowingutapp.viewmodels.interfaces

import com.google.firebase.auth.FirebaseUser

interface IRegistrationViewModel {

    fun handleSignIn(email: String, password: String)

    fun handleSignUp(email: String, password: String)

    fun handleIsSignedIn(callback: (FirebaseUser?) -> Unit)

    fun handlePage(position: Int)

    fun handleIsPasswordsEquals(password: String, confirmedPassword: String)


}